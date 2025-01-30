package ty.bre.nimbus.domain.util

import kotlinx.serialization.Serializable

@Serializable
sealed class Screen {
    @Serializable
    data object FirstScreen : Screen()

    @Serializable
    data object SecondScreen : Screen()
}