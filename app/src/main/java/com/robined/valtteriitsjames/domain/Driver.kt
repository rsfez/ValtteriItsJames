package com.robined.valtteriitsjames.domain

import androidx.compose.runtime.Stable
import kotlinx.serialization.Serializable

@Stable
@Serializable
enum class Driver(private val driverName: String, val team: Team) {
    VERSTAPPEN("Verstappen", Team.RED_BULL_RACING),
    NORRIS("Norris", Team.MC_LAREN),
    PIASTRI("Piastri", Team.MC_LAREN),
    LECLERC("Leclerc", Team.FERRARI),
    SAINZ("Sainz", Team.FERRARI),
    HAMILTON("Hamilton", Team.MERCEDES),
    RUSSELL("Russell", Team.MERCEDES),
    ALONSO("Alonso", Team.ASTON_MARTIN),
    STROLL("Stroll", Team.ASTON_MARTIN),
    TSUNODA("Tsunoda", Team.RB),
    LAWSON("Lawson", Team.RB),
    HULKENBERG("Hulkenberg", Team.HAAS),
    MAGNUSSEN("Magnussen", Team.HAAS),
    GASLY("Gasly", Team.ALPINE),
    OCON("Ocon", Team.ALPINE),
    ALBON("Albon", Team.WILLIAMS),
    COLAPINTO("Colapinto", Team.WILLIAMS);

    fun driverName() = driverName.uppercase()
}