package com.robined.valtteriitsjames.ui.teamradio

import android.os.Bundle
import androidx.compose.runtime.Stable
import androidx.navigation.NavType
import com.robined.valtteriitsjames.domain.Driver
import com.robined.valtteriitsjames.domain.Message
import com.robined.valtteriitsjames.domain.Message.Type
import com.robined.valtteriitsjames.domain.Team
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json

@Stable
@Serializable
internal data class TeamRadioUIState(
    val driver: Driver,
    val messages: ImmutableList<Message> = persistentListOf()
) {
    enum class Presets(val state: TeamRadioUIState) {
        VERSTAPPEN(
            TeamRadioUIState(
                driver = Driver("Verstappen", Team.RED_BULL_RACING), messages = persistentListOf(
                    Message(
                        "Whoa!\nThere's a giant lizard on the track!",
                        Type.DRIVER
                    ),
                    Message(
                        "Came face-to-face with Godzilla there mate",
                        Type.TEAM
                    ),
                )
            )
        ),
        NORRIS(
            TeamRadioUIState(
                driver = Driver("Norris", Team.MC_LAREN), messages = persistentListOf(
                    Message(
                        "Ahhhh, F**k my championship",
                        Type.DRIVER
                    ),
                )
            )
        ),
        PIASTRI(TeamRadioUIState(driver = Driver("Piastri", Team.MC_LAREN))),
        LECLERC(
            TeamRadioUIState(
                driver = Driver("Leclerc", Team.FERRARI), messages = persistentListOf(
                    Message("Why am I bouncing so much?", type = Type.DRIVER),
                    Message("We are checking...", type = Type.TEAM),
                )
            )
        ),
        SAINZ(
            TeamRadioUIState(
                driver = Driver("Sainz", Team.FERRARI), messages = persistentListOf(
                    Message("More phase 1 break release, for turn 1", type = Type.TEAM),
                    Message("I don't know what that means", type = Type.DRIVER),
                )
            )
        ),
        HAMILTON(
            TeamRadioUIState(
                driver = Driver("Hamilton", Team.MERCEDES),
                messages = persistentListOf()
            )
        ),
        RUSSELL(
            TeamRadioUIState(
                driver = Driver("Russell", Team.MERCEDES),
                messages = persistentListOf()
            )
        ),
        ALONSO(
            TeamRadioUIState(
                driver = Driver("Alonso", Team.ASTON_MARTIN), messages = persistentListOf(
                    Message(
                        "Wow.",
                        Type.DRIVER
                    ),
                )
            )
        ),
        STROLL(
            TeamRadioUIState(
                driver = Driver("Stroll", Team.ASTON_MARTIN), messages = persistentListOf(
                    Message(
                        "I'm beached.",
                        Type.DRIVER
                    ),
                )
            )
        ),
        TSUNODA(
            TeamRadioUIState(
                driver = Driver("Tsunoda", Team.RB),
                messages = persistentListOf()
            )
        ),
        LAWSON(TeamRadioUIState(driver = Driver("Lawson", Team.RB), messages = persistentListOf())),
        HULKENBERG(
            TeamRadioUIState(
                driver = Driver("Hulkenberg", Team.HAAS),
                messages = persistentListOf()
            )
        ),
        MAGNUSSEN(
            TeamRadioUIState(
                driver = Driver("Magnussen", Team.HAAS),
                messages = persistentListOf()
            )
        ),
        GASLY(
            TeamRadioUIState(
                driver = Driver("Gasly", Team.ALPINE),
                messages = persistentListOf()
            )
        ),
        OCON(TeamRadioUIState(driver = Driver("Ocon", Team.ALPINE), messages = persistentListOf())),
        ALBON(
            TeamRadioUIState(
                driver = Driver("Albon", Team.WILLIAMS),
                messages = persistentListOf()
            )
        ),
        COLAPINTO(
            TeamRadioUIState(
                driver = Driver("Colapinto", Team.WILLIAMS),
                messages = persistentListOf()
            )
        );
    }
}


val DriverType = object : NavType<Driver>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): Driver? = bundle.getString(key)?.let {
        Json.decodeFromString<Driver>(it)
    }

    override fun parseValue(value: String): Driver = Json.decodeFromString<Driver>(value)

    override fun put(bundle: Bundle, key: String, value: Driver) {
        bundle.putString(key, Json.encodeToString(Driver.serializer(), value))
    }

    override fun serializeAsValue(value: Driver): String =
        Json.encodeToString(Driver.serializer(), value)
}

val MessageListType = object : NavType<ImmutableList<Message>>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): ImmutableList<Message>? =
        bundle.getString(key)?.let {
            Json.decodeFromString<List<Message>>(it).toPersistentList()
        }

    override fun parseValue(value: String): ImmutableList<Message> =
        Json.decodeFromString<List<Message>>(value).toPersistentList()

    override fun put(bundle: Bundle, key: String, value: ImmutableList<Message>) {
        bundle.putString(key, Json.encodeToString(ListSerializer(Message.serializer()), value))
    }

    override fun serializeAsValue(value: ImmutableList<Message>): String =
        Json.encodeToString(ListSerializer(Message.serializer()), value)
}
