package com.robined.valtteriitsjames

import android.media.MediaPlayer
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.tooling.preview.Preview
import com.robined.valtteriitsjames.domain.Driver
import com.robined.valtteriitsjames.domain.Message
import com.robined.valtteriitsjames.domain.Message.Type
import com.robined.valtteriitsjames.ds.primaryBg
import com.robined.valtteriitsjames.ui.RadioHeader
import com.robined.valtteriitsjames.ui.TeamRadioUIState
import com.robined.valtteriitsjames.ui.message.MessagesPanel
import kotlinx.collections.immutable.persistentListOf

@Composable
fun TeamRadio(
    state: TeamRadioUIState,
    isPreview: Boolean = LocalInspectionMode.current
) {
    val driver = state.driver

    if (!isPreview) {
        val player = MediaPlayer.create(LocalContext.current, R.raw.team_radio_f1_fx)

        LaunchedEffect(Unit) {
            player.start()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(primaryBg)
    ) {
        RadioHeader(driver)
        MessagesPanel(state)
    }
}

@Preview
@Composable
private fun TeamRadioPreview() {
    TeamRadio(
        state = TeamRadioUIState(
            driver = Driver.NORRIS,
            messages = persistentListOf(
                Message("Ahhh, f*ck my championship", type = Type.DRIVER),
            ),
        )
    )
}

@Preview
@Composable
fun LeclercPreview() {
    TeamRadio(
        state = TeamRadioUIState(
            driver = Driver.LECLERC,
            messages = persistentListOf(
                Message("Why am I bouncing so much?", type = Type.DRIVER),
                Message("We are checking...", type = Type.TEAM),
            ),
        )
    )
}

@Preview
@Composable
fun AlonsoPreview() {
    TeamRadio(
        state = TeamRadioUIState(
            driver = Driver.ALONSO,
            messages = persistentListOf(
                Message("Wow.", type = Type.DRIVER),
            ),
        )
    )
}

@Preview
@Composable
fun StrollPreview() {
    TeamRadio(
        state = TeamRadioUIState(
            driver = Driver.STROLL,
            messages = persistentListOf(
                Message("I'm beached.", type = Type.DRIVER),
            ),
        )
    )
}

