package com.robined.valtteriitsjames.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.robined.valtteriitsjames.R
import com.robined.valtteriitsjames.ds.PrimaryButton
import com.robined.valtteriitsjames.ui.teamradio.TeamRadioUIState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun DriverPresetSelection(
    onPresetSelected: (TeamRadioUIState) -> Unit
) {
    val bottomSheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }

    PrimaryButton(
        onClick = { showBottomSheet = true },
        content = { Text(stringResource(R.string.choose_a_driver_preset_button)) }
    )

    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                showBottomSheet = false
            },
            sheetState = bottomSheetState,
        ) {
            val presets = TeamRadioUIState.Presets.entries
            LazyColumn {
                items(presets.size) { index ->
                    val presetState = presets[index].state
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                onPresetSelected(presetState)
                                showBottomSheet = false
                            }
                    ) {
                        BottomSheetSelectionRow(
                            color = presetState.driver.team.color,
                            text = presetState.driver.driverNameNotFormatted()
                        )
                    }
                }
            }
        }
    }
}

