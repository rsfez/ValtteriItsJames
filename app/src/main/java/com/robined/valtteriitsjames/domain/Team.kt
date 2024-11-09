package com.robined.valtteriitsjames.domain

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import com.robined.valtteriitsjames.R
import com.robined.valtteriitsjames.ds.theme.alpine
import com.robined.valtteriitsjames.ds.theme.astonMartin
import com.robined.valtteriitsjames.ds.theme.ferrari
import com.robined.valtteriitsjames.ds.theme.haas
import com.robined.valtteriitsjames.ds.theme.kickSauber
import com.robined.valtteriitsjames.ds.theme.mcLaren
import com.robined.valtteriitsjames.ds.theme.mercedes
import com.robined.valtteriitsjames.ds.theme.rb
import com.robined.valtteriitsjames.ds.theme.redBullRacing
import com.robined.valtteriitsjames.ds.theme.williams
import kotlinx.serialization.Serializable

@Stable
@Serializable
enum class Team(
    val nameContentDescription: String,
    val color: Color,
    @DrawableRes val logoResId: Int,
) {
    RED_BULL_RACING(
        nameContentDescription = "Red Bull Racing",
        color = redBullRacing,
        logoResId = R.drawable.red_bull_racing,
    ),
    MC_LAREN(
        nameContentDescription = "McLaren",
        color = mcLaren,
        logoResId = R.drawable.mc_laren,
    ),
    FERRARI(
        nameContentDescription = "Ferrari",
        color = ferrari,
        logoResId = R.drawable.ferrari,
    ),
    MERCEDES(
        nameContentDescription = "Mercedes",
        color = mercedes,
        logoResId = R.drawable.mercedes,
    ),
    ASTON_MARTIN(
        nameContentDescription = "Aston Martin",
        color = astonMartin,
        logoResId = R.drawable.aston_martin,
    ),
    RB(
        nameContentDescription = "RB",
        color = rb,
        logoResId = R.drawable.rb,
    ),
    HAAS(
        nameContentDescription = "Haas",
        color = haas,
        logoResId = R.drawable.haas,
    ),
    ALPINE(
        nameContentDescription = "Alpine",
        color = alpine,
        logoResId = R.drawable.alpine,
    ),
    KICK_SAUBER(
        nameContentDescription = "Kick Sauber",
        color = kickSauber,
        logoResId = R.drawable.kick_sauber,
    ),
    WILLIAMS(
        nameContentDescription = "Williams",
        color = williams,
        logoResId = R.drawable.williams,
    );
}