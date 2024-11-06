package com.robined.valtteriitsjames

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.robined.valtteriitsjames.domain.Message
import com.robined.valtteriitsjames.ui.MessageListType
import com.robined.valtteriitsjames.ui.TeamRadioUIState
import com.robined.valtteriitsjames.ui.samples
import kotlinx.collections.immutable.ImmutableList
import kotlin.reflect.typeOf

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Box(modifier = Modifier.safeDrawingPadding()) {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = Home) {
                    composable<Home> {
                        Home(onNavigateToRandomTeamRadio = {
                            navController.navigate(samples.random())
                        })
                    }
                    composable<TeamRadioUIState>(
                        typeMap = mapOf(
                            typeOf<ImmutableList<Message>>() to MessageListType
                        )
                    ) { navBackStackEntry ->
                        val state: TeamRadioUIState = navBackStackEntry.toRoute()
                        TeamRadio(state = state)
                    }
                }
            }
        }
    }
}