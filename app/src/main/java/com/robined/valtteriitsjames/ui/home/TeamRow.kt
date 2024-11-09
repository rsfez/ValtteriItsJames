package com.robined.valtteriitsjames.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.robined.valtteriitsjames.domain.Team

@Composable
internal fun TeamRow(team: Team) {
    Row(modifier = Modifier.height(IntrinsicSize.Min)) {
        Box(
            modifier = Modifier
                .width(6.dp)
                .fillMaxHeight()
                .background(team.color)
        )
        Box(modifier = Modifier.width(16.dp))
        Text(team.nameContentDescription)
    }
}