package com.robined.valtteriitsjames.domain

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import com.robined.valtteriitsjames.R
import com.robined.valtteriitsjames.ds.alpine
import com.robined.valtteriitsjames.ds.astonMartin
import com.robined.valtteriitsjames.ds.ferrari
import com.robined.valtteriitsjames.ds.haas
import com.robined.valtteriitsjames.ds.kickSauber
import com.robined.valtteriitsjames.ds.mcLaren
import com.robined.valtteriitsjames.ds.mercedes
import com.robined.valtteriitsjames.ds.rb
import com.robined.valtteriitsjames.ds.redBullRacing
import com.robined.valtteriitsjames.ds.williams

@Stable
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
        nameContentDescription = "Red Bull Visa CashApp",
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