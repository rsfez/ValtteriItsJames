package com.robined.valtteriitsjames.ui.teamradio

import android.annotation.SuppressLint
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateInt
import androidx.compose.animation.core.rememberTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.robined.valtteriitsjames.domain.DriverPreset
import com.robined.valtteriitsjames.domain.Message
import com.robined.valtteriitsjames.domain.Message.Type
import com.robined.valtteriitsjames.domain.Team
import com.robined.valtteriitsjames.ds.Font
import com.robined.valtteriitsjames.ds.Spacing.xLarge
import com.robined.valtteriitsjames.ds.Spacing.xxxLarge
import com.robined.valtteriitsjames.ds.f1Font
import com.robined.valtteriitsjames.ds.transparent
import com.robined.valtteriitsjames.ds.white
import com.robined.valtteriitsjames.ds.white_10
import com.robined.valtteriitsjames.ds.white_2
import com.robined.valtteriitsjames.ds.white_20
import com.robined.valtteriitsjames.ui.preview.TeamProvider
import kotlinx.collections.immutable.persistentListOf

@Composable
internal fun MessagesPanel(state: TeamRadioUIState) {
    val team = state.driver.team
    val colorStops = arrayOf(
        0.0f to white_20,
        0.05f to white_10,
        0.3f to white_2,
        1f to transparent
    )
    Box(modifier = Modifier.height(xLarge))
    Column(modifier = Modifier.background(Brush.verticalGradient(colorStops = colorStops))) {
        state.messages.forEach {
            when (it.type) {
                Type.DRIVER -> DriverMessage(team = team, driverMessage = it.get())
                Type.TEAM -> TeamMessage(teamMessage = it.get())
            }
            Box(modifier = Modifier.height(xxxLarge))
        }
    }
}

@Preview
@Composable
private fun MessagesPanelPreview() {
    MessagesPanel(
        state = TeamRadioUIState(
            driver = DriverPreset.VERSTAPPEN.driver,
            messages = persistentListOf(
                Message("Whoa!\nThere's a giant lizard on the track!", type = Type.DRIVER),
                Message("Came face-to-face with Godzilla there mate", type = Type.TEAM),
            ),
        )
    )
}

@Composable
private fun TeamMessage(teamMessage: String) {
    Message(message = teamMessage, color = white, textAlign = TextAlign.Start)
}

@Preview
@Composable
private fun TeamMessagePreview() {
    TeamMessage(
        teamMessage = "\"FROM TEAM\""
    )
}

@Composable
private fun DriverMessage(team: Team, driverMessage: String) {
    Message(
        message = driverMessage,
        color = team.color,
        textAlign = TextAlign.End
    )
}

@Preview
@Composable
private fun DriverMessagePreview(@PreviewParameter(TeamProvider::class) team: Team) {
    DriverMessage(
        team = team,
        driverMessage = "\"FROM DRIVER\""
    )
}

@SuppressLint("UnusedTransitionTargetStateParameter")
@Composable
private fun Message(
    message: String,
    color: Color,
    textAlign: TextAlign,
    isPreview: Boolean = LocalInspectionMode.current
) {
    val currentState = remember { MutableTransitionState(0) }
    currentState.targetState = message.length - 1
    val transition = rememberTransition(currentState, label = "message state")
    val typeWriterTransition: Int by transition.animateInt(
        transitionSpec = { tween(durationMillis = message.length * 10, easing = LinearEasing) },
        label = "typewriter",
        targetValueByState = { currentState.currentState },
    )
    val text = buildAnnotatedString {
        if (isPreview) {
            append(message)
        } else {
            append(message.substring(0..typeWriterTransition))
            withStyle(SpanStyle(color = transparent)) {
                append(message.substring(typeWriterTransition + 1..currentState.targetState))
            }
        }
    }
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = xLarge)
            .padding(top = xLarge),
        text = text,
        textAlign = textAlign,
        fontFamily = f1Font,
        fontStyle = FontStyle.Italic,
        fontSize = Font.xLarge,
        fontWeight = FontWeight.Bold,
        color = color,
    )
}