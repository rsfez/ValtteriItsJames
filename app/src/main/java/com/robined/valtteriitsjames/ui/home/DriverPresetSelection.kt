package com.robined.valtteriitsjames.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.robined.valtteriitsjames.R
import com.robined.valtteriitsjames.domain.Message
import com.robined.valtteriitsjames.domain.Team
import com.robined.valtteriitsjames.ds.PrimaryButton
import com.robined.valtteriitsjames.ds.Spacing.small
import com.robined.valtteriitsjames.ui.teamradio.TeamRadioUIState

@Composable
internal fun DriverPresetSelection(
    driverNameState: MutableState<String>,
    teamState: MutableState<Team?>,
    messages: SnapshotStateList<Message>
) {
    var driverPresetExpanded by remember { mutableStateOf(false) }
    PrimaryButton(
        modifier = Modifier.fillMaxWidth(),
        onClick = { driverPresetExpanded = true },
        content = { Text(stringResource(R.string.select_a_driver_preset_button)) }
    )
    DropdownMenu(
        expanded = driverPresetExpanded,
        onDismissRequest = { driverPresetExpanded = false }
    ) {
        TeamRadioUIState.Presets.entries.map(TeamRadioUIState.Presets::state)
            .forEach { presetState ->
                DropdownMenuItem(
                    text = {
                        Row(modifier = Modifier.height(IntrinsicSize.Min)) {
                            Box(
                                modifier = Modifier
                                    .width(small)
                                    .fillMaxHeight()
                                    .background(presetState.driver.team.color)
                            )
                            Box(modifier = Modifier.width(16.dp))
                            Text(presetState.driver.driverNameNotFormatted())
                        }
                    },
                    onClick = {
                        driverNameState.value = presetState.driver.driverNameNotFormatted()
                        teamState.value = presetState.driver.team
                        messages.addAll(presetState.messages)
                        driverPresetExpanded = false
                    }
                )
            }
    }
}