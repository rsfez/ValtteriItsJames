package com.robined.valtteriitsjames.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.robined.valtteriitsjames.domain.Driver
import com.robined.valtteriitsjames.domain.Message
import com.robined.valtteriitsjames.domain.Team
import com.robined.valtteriitsjames.ds.Spacing.xLarge
import com.robined.valtteriitsjames.ds.Spacing.small
import com.robined.valtteriitsjames.ui.teamradio.TeamRadioUIState
import kotlinx.collections.immutable.toPersistentList

@Composable
internal fun BoxScope.Footer(
    driverName: String,
    team: Team?,
    messages: SnapshotStateList<Message>,
    onNavigateToTeamRadio: (TeamRadioUIState) -> Unit,
    onNavigateToRandomTeamRadio: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.BottomCenter)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = xLarge, vertical = small),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                modifier = Modifier.fillMaxWidth(),
                enabled = team != null,
                onClick = {
                    onNavigateToTeamRadio(
                        produceTeamRadioUiState(
                            driverName = driverName,
                            team = team,
                            messages = messages
                        )
                    )
                },
                content = { Text("Go!") }
            )
            Text(
                modifier = Modifier.padding(vertical = small),
                text = "or",
                textAlign = TextAlign.Center
            )
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = onNavigateToRandomTeamRadio,
                content = { Text("Random!") }
            )
        }
    }
}

private fun produceTeamRadioUiState(
    driverName: String,
    team: Team?,
    messages: SnapshotStateList<Message>
) = TeamRadioUIState(
    driver = Driver(
        driverName = driverName,
        team = team!!
    ),
    messages = messages.toPersistentList()
)
