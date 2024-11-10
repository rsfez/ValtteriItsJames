package com.robined.valtteriitsjames.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.robined.valtteriitsjames.R
import com.robined.valtteriitsjames.domain.Driver
import com.robined.valtteriitsjames.domain.Message
import com.robined.valtteriitsjames.domain.Team
import com.robined.valtteriitsjames.ds.PrimaryButton
import com.robined.valtteriitsjames.ds.Spacing.small
import com.robined.valtteriitsjames.ds.Spacing.xLarge
import com.robined.valtteriitsjames.ds.theme.AppTheme
import com.robined.valtteriitsjames.ui.teamradio.TeamRadioUIState
import kotlinx.collections.immutable.toPersistentList

@Composable
internal fun Footer(
    driverName: String,
    team: Team?,
    messages: SnapshotStateList<Message>,
    onNavigateToTeamRadio: (TeamRadioUIState) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = xLarge)
            .padding(bottom = small)
    ) {
        val enabled = team != null &&
                driverName.isNotBlank() &&
                messages.filterNot(Message::isEmpty).isNotEmpty()
        PrimaryButton(
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
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
    messages = messages.filterNot(Message::isEmpty).toPersistentList()
)

@Composable
@Preview
fun FooterPreview() {
    val preset = TeamRadioUIState.Presets.NORRIS.state
    AppTheme {
        Footer(
            driverName = preset.driver.driverNameNotFormatted(),
            team = preset.driver.team,
            messages = preset.messages.toMutableStateList(),
            onNavigateToTeamRadio = {}
        )
    }
}
