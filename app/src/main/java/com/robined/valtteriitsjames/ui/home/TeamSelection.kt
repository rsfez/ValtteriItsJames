package com.robined.valtteriitsjames.ui.home

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.robined.valtteriitsjames.R
import com.robined.valtteriitsjames.domain.Team
import com.robined.valtteriitsjames.ds.PrimaryButton

@Composable
internal fun TeamSelection(teamState: MutableState<Team?>) {
    var teamSelectionExpanded by remember { mutableStateOf(false) }
    PrimaryButton(
        modifier = Modifier.fillMaxWidth(),
        onClick = { teamSelectionExpanded = true },
        content = {
            teamState.value?.let {
                TeamRow(it)
            } ?: Text(stringResource(R.string.choose_a_team_button))
        }
    )
    DropdownMenu(
        expanded = teamSelectionExpanded,
        onDismissRequest = { teamSelectionExpanded = false }
    ) {
        Team.entries.forEach {
            DropdownMenuItem(
                text = { TeamRow(team = it) },
                onClick = {
                    teamState.value = it
                    teamSelectionExpanded = false
                }
            )
        }
    }
}