package com.robined.valtteriitsjames.ui.home

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.robined.valtteriitsjames.domain.Message
import com.robined.valtteriitsjames.domain.Team
import com.robined.valtteriitsjames.ds.Spacing.xLarge
import com.robined.valtteriitsjames.ui.teamradio.TeamRadioUIState
import kotlinx.serialization.Serializable

@Serializable
object Home

@Composable
internal fun Home(
    onNavigateToTeamRadio: (TeamRadioUIState) -> Unit,
    onNavigateToRandomTeamRadio: () -> Unit
) {
    val driverNameState = remember { mutableStateOf("") }
    val teamState = remember { mutableStateOf<Team?>(null) }
    val messages = remember { mutableStateListOf<Message>() }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .padding(horizontal = xLarge)
                .padding(top = xLarge)
                .scrollable(
                    state = rememberScrollState(),
                    orientation = Orientation.Vertical
                ),
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
                label = { Text("Driver") }
            )
            Box(modifier = Modifier.height(xLarge))
            TeamSelection(teamState = teamState)
            Box(modifier = Modifier.height(xLarge))
            MessagesSection(messages = messages)
        }
        Footer(
            driverName = driverNameState.value,
            team = teamState.value,
            messages = messages,
            onNavigateToTeamRadio = onNavigateToTeamRadio,
            onNavigateToRandomTeamRadio = onNavigateToRandomTeamRadio
        )
    }
}
