package com.robined.valtteriitsjames

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.serialization.Serializable

@Serializable
object Home

@Composable
fun Home(onNavigateToRandomTeamRadio: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        Button(
            modifier = Modifier.align(Alignment.Center),
            onClick = onNavigateToRandomTeamRadio,
            content = { Text("Random!") }
        )
    }
}