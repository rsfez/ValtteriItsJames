package com.robined.valtteriitsjames.ui

import android.os.Bundle
import androidx.compose.runtime.Stable
import androidx.navigation.NavType
import com.robined.valtteriitsjames.domain.Driver
import com.robined.valtteriitsjames.domain.Message
import com.robined.valtteriitsjames.domain.Message.Type
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json

@Stable
@Serializable
data class TeamRadioUIState(
    val driver: Driver,
    val messages: ImmutableList<Message> = persistentListOf()
)

val MessageListType = object : NavType<ImmutableList<Message>>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): ImmutableList<Message>? {
        return bundle.getString(key)?.let {
            val list: List<Message> = Json.decodeFromString(it)
            list.toPersistentList()
        }
    }

    override fun parseValue(value: String): ImmutableList<Message> {
        val list: List<Message> = Json.decodeFromString(value)
        return list.toPersistentList()
    }

    override fun put(bundle: Bundle, key: String, value: ImmutableList<Message>) {
        bundle.putString(key, Json.encodeToString(ListSerializer(Message.serializer()), value))
    }

    override fun serializeAsValue(value: ImmutableList<Message>): String {
        return Json.encodeToString(ListSerializer(Message.serializer()), value)
    }
}

val samples = persistentListOf<TeamRadioUIState>(
    TeamRadioUIState(
        driver = Driver.VERSTAPPEN,
        messages = persistentListOf(
            Message(
                "Whoa!\nThere's a giant lizard on the track!",
                Message.Type.DRIVER
            ),
            Message(
                "Came face-to-face with Godzilla there mate",
                Message.Type.TEAM
            ),
        ),
    ),
    TeamRadioUIState(
        driver = Driver.NORRIS,
        messages = persistentListOf(
            Message(
                "Ahhhh, F**k my championship",
                Message.Type.DRIVER
            ),
        ),
    ),
    TeamRadioUIState(
        driver = Driver.LECLERC,
        messages = persistentListOf(
            Message("Why am I bouncing so much?", type = Type.DRIVER),
            Message("We are checking...", type = Type.TEAM),
        ),
    ),
    TeamRadioUIState(
        driver = Driver.SAINZ,
        messages = persistentListOf(
            Message("More phase 1 break release, for turn 1", type = Type.TEAM),
            Message("I don't know what that means", type = Type.DRIVER),
        ),
    ),
    TeamRadioUIState(
        driver = Driver.ALONSO,
        messages = persistentListOf(
            Message(
                "Wow.",
                Message.Type.DRIVER
            ),
        ),
    ),
    TeamRadioUIState(
        driver = Driver.STROLL,
        messages = persistentListOf(
            Message(
                "I'm beached.",
                Message.Type.DRIVER
            ),
        ),
    ),
)
