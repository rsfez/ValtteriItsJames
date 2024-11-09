package com.robined.valtteriitsjames.domain

import androidx.compose.runtime.Stable
import kotlinx.serialization.Serializable

@Stable
@Serializable
data class Message(private val content: String, val type: Type) {
    fun get() = "\"${content.uppercase()}\""
    fun getNotFormatted() = content
    fun isEmpty() = content.isEmpty()

    enum class Type {
        TEAM,
        DRIVER
    }
}
