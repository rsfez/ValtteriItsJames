package com.robined.valtteriitsjames.ui.home

import android.os.Bundle
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.robined.valtteriitsjames.R
import com.robined.valtteriitsjames.domain.Message
import com.robined.valtteriitsjames.domain.Team
import com.robined.valtteriitsjames.ds.Spacing.xLarge
import com.robined.valtteriitsjames.ui.teamradio.MessageListType
import com.robined.valtteriitsjames.ui.teamradio.TeamRadioUIState
import kotlinx.collections.immutable.toPersistentList
import kotlinx.serialization.Serializable

@Serializable
object Home

@Composable
internal fun Home(
    onNavigateToTeamRadio: (TeamRadioUIState) -> Unit,
    onNavigateToRandomTeamRadio: () -> Unit
) {
    val driverNameState = rememberSaveable { mutableStateOf("") }
    val teamState = rememberSaveable { mutableStateOf<Team?>(null) }
    val messages = rememberMessages()

    Scaffold(bottomBar = {
        Footer(
            driverName = driverNameState.value,
            team = teamState.value,
            messages = messages,
            onNavigateToTeamRadio = onNavigateToTeamRadio,
            onNavigateToRandomTeamRadio = onNavigateToRandomTeamRadio
        )
    }) { contentPadding ->
        Column(
            modifier = Modifier
                .padding(horizontal = xLarge)
                .padding(top = xLarge + contentPadding.calculateTopPadding())
                .padding(bottom = contentPadding.calculateBottomPadding())
        ) {
            DriverPresetSelection(
                driverNameState = driverNameState,
                teamState = teamState,
                messages = messages
            )
            Box(modifier = Modifier.height(xLarge))
            TextField(
                value = driverNameState.value,
                onValueChange = { driverNameState.value = it },
                label = { Text(stringResource(R.string.driver)) }
            )
            Box(modifier = Modifier.height(xLarge))
            TeamSelection(teamState = teamState)
            Box(modifier = Modifier.height(xLarge))
            MessagesSection(messages = messages)
        }
    }
}

@Composable
private fun rememberMessages(vararg elements: Message): SnapshotStateList<Message> =
    rememberSaveable(saver = snapshotStateListSaver()) {
        elements.toList().toMutableStateList()
    }

private fun snapshotStateListSaver() = Saver<SnapshotStateList<Message>, Bundle>(
    save = {
        Bundle().apply {
            putString(
                MESSAGES_BUNDLE_KEY,
                MessageListType.serializeAsValue(it.toPersistentList())
            )
        }
    },
    restore = { bundle ->
        bundle.getString(MESSAGES_BUNDLE_KEY).let {
            MessageListType.parseValue(it!!)
        }.toMutableStateList()
    },
)

private const val MESSAGES_BUNDLE_KEY = "messages"