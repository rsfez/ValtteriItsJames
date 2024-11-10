package com.robined.valtteriitsjames.ui.teamradio

import android.media.MediaPlayer
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.layer.drawLayer
import androidx.compose.ui.graphics.rememberGraphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.tooling.preview.Preview
import com.robined.valtteriitsjames.R
import com.robined.valtteriitsjames.domain.DriverPreset
import com.robined.valtteriitsjames.domain.Message
import com.robined.valtteriitsjames.domain.Message.Type
import com.robined.valtteriitsjames.ds.theme.primaryBg
import kotlinx.collections.immutable.persistentListOf

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun TeamRadio(
    state: TeamRadioUIState,
    isPreview: Boolean = LocalInspectionMode.current
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val graphicsLayer = rememberGraphicsLayer()
    val shareRequested = remember { mutableStateOf(false) }
    val driver = state.driver

    if (!isPreview) {
        val player = MediaPlayer.create(LocalContext.current, R.raw.team_radio_f1_fx)

        LaunchedEffect(Unit) {
            player.start()
        }
        ImageShare(
            shareRequested = shareRequested,
            graphicsLayer = graphicsLayer,
            snackbarHostState = snackbarHostState
        )
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(primaryBg)
                .combinedClickable(
                    onClick = {},
                    onLongClick = { shareRequested.value = true }
                ),
        ) {
            Column(modifier = Modifier
                .padding(padding)
                .drawWithContent {
                    graphicsLayer.record {
                        this@drawWithContent.drawContent()
                    }
                    drawLayer(graphicsLayer)
                }
            ) {
                RadioHeader(driver)
                MessagesPanel(state)
            }
        }
    }
}

@Preview
@Composable
private fun TeamRadioPreview() {
    TeamRadio(
        state = TeamRadioUIState(
            driver = DriverPreset.NORRIS.driver,
            messages = persistentListOf(
                Message("Ahhh, f*ck my championship", type = Type.DRIVER),
            ),
        )
    )
}

@Preview
@Composable
private fun LeclercPreview() {
    TeamRadio(
        state = TeamRadioUIState(
            driver = DriverPreset.LECLERC.driver,
            messages = persistentListOf(
                Message("Why am I bouncing so much?", type = Type.DRIVER),
                Message("We are checking...", type = Type.TEAM),
            ),
        )
    )
}

@Preview
@Composable
private fun AlonsoPreview() {
    TeamRadio(
        state = TeamRadioUIState(
            driver = DriverPreset.ALONSO.driver,
            messages = persistentListOf(
                Message("Wow.", type = Type.DRIVER),
            ),
        )
    )
}

@Preview
@Composable
private fun StrollPreview() {
    TeamRadio(
        state = TeamRadioUIState(
            driver = DriverPreset.STROLL.driver,
            messages = persistentListOf(
                Message("I'm beached.", type = Type.DRIVER),
            ),
        )
    )
}

