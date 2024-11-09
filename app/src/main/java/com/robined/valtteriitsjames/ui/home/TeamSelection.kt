package com.robined.valtteriitsjames.ui.home

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
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
import com.robined.valtteriitsjames.domain.Team

@Composable
internal fun TeamSelection(teamState: MutableState<Team?>) {
    var teamSelectionExpanded by remember { mutableStateOf(false) }
    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = { teamSelectionExpanded = true },
        content = {
            teamState.value?.let {
                TeamRow(it)
            } ?: Text("Choose a team")
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