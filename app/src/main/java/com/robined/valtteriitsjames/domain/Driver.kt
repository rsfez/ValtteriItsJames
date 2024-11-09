package com.robined.valtteriitsjames.domain

import androidx.compose.runtime.Stable
import kotlinx.serialization.Serializable

@Stable
@Serializable
data class Driver(private val driverName: String, val team: Team) {
    fun driverName() = driverName.uppercase()
    fun driverNameNotFormatted() = driverName
}

enum class DriverPreset(val driver: Driver) {
    VERSTAPPEN(Driver("Verstappen", Team.RED_BULL_RACING)),
    NORRIS(Driver("Norris", Team.MC_LAREN)),
    PIASTRI(Driver("Piastri", Team.MC_LAREN)),
    LECLERC(Driver("Leclerc", Team.FERRARI)),
    SAINZ(Driver("Sainz", Team.FERRARI)),
    HAMILTON(Driver("Hamilton", Team.MERCEDES)),
    RUSSELL(Driver("Russell", Team.MERCEDES)),
    ALONSO(Driver("Alonso", Team.ASTON_MARTIN)),
    STROLL(Driver("Stroll", Team.ASTON_MARTIN)),
    TSUNODA(Driver("Tsunoda", Team.RB)),
    LAWSON(Driver("Lawson", Team.RB)),
    HULKENBERG(Driver("Hulkenberg", Team.HAAS)),
    MAGNUSSEN(Driver("Magnussen", Team.HAAS)),
    GASLY(Driver("Gasly", Team.ALPINE)),
    OCON(Driver("Ocon", Team.ALPINE)),
    ALBON(Driver("Albon", Team.WILLIAMS)),
    COLAPINTO(Driver("Colapinto", Team.WILLIAMS));
}