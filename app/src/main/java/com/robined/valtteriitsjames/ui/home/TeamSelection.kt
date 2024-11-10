package com.robined.valtteriitsjames.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.robined.valtteriitsjames.R
import com.robined.valtteriitsjames.domain.Team
import com.robined.valtteriitsjames.ds.PrimaryButton
import com.robined.valtteriitsjames.ds.Spacing.medium

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun TeamSelection(teamState: MutableState<Team?>) {
    val bottomSheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }

    PrimaryButton(
        modifier = Modifier.fillMaxWidth(),
        onClick = { showBottomSheet = true },
        content = {
            teamState.value?.let {
                Row(modifier = Modifier.height(IntrinsicSize.Min)) {
                    Box(
                        modifier = Modifier
                            .width(6.dp)
                            .fillMaxHeight()
                            .background(it.color)
                    )
                    Box(modifier = Modifier.width(medium))
                    Text(it.nameContentDescription)
                }
            } ?: Text(stringResource(R.string.choose_a_team_button))
        }
    )

    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                showBottomSheet = false
            },
            sheetState = bottomSheetState,
        ) {
            val teams = Team.entries
            LazyColumn {
                items(teams.size) { index ->
                    val team = teams[index]
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                teamState.value = team
                                showBottomSheet = false
                            }
                    ) {
                        BottomSheetSelectionRow(
                            color = team.color,
                            text = team.nameContentDescription
                        )
                    }
                }
            }
        }
    }
}