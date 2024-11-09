package com.robined.valtteriitsjames.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.robined.valtteriitsjames.R
import com.robined.valtteriitsjames.domain.Driver
import com.robined.valtteriitsjames.domain.Message
import com.robined.valtteriitsjames.domain.Team
import com.robined.valtteriitsjames.ds.Font
import com.robined.valtteriitsjames.ds.PrimaryButton
import com.robined.valtteriitsjames.ds.Spacing.small
import com.robined.valtteriitsjames.ds.Spacing.xLarge
import com.robined.valtteriitsjames.ui.teamradio.TeamRadioUIState
import kotlinx.collections.immutable.toPersistentList

@Composable
internal fun Footer(
    driverName: String,
    team: Team?,
    messages: SnapshotStateList<Message>,
    onNavigateToTeamRadio: (TeamRadioUIState) -> Unit,
    onNavigateToRandomTeamRadio: () -> Unit
) {
    Box(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = xLarge)
                .padding(bottom = small),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            PrimaryButton(
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
                content = { Text(stringResource(R.string.go_button)) }
            )
            Text(
                modifier = Modifier.padding(vertical = small),
                text = "or",
                textAlign = TextAlign.Center
            )
            PrimaryButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = onNavigateToRandomTeamRadio,
                content = { Text(stringResource(R.string.random_button)) }
            )
            Text(
                text = stringResource(R.string.sharing_hint),
                color = Color.Gray,
                fontSize = Font.medium,
                textAlign = TextAlign.Center
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
