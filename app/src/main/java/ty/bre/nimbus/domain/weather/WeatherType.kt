package ty.bre.nimbus.domain.weather

import androidx.annotation.DrawableRes
import ty.bre.nimbus.R

sealed class WeatherType(
    val weatherDesc: String,
    @DrawableRes val iconRes: Int
) {
    data object ClearSky : WeatherType(
        weatherDesc = "Clear sky",
        iconRes = R.drawable.ic_sunny
    )
}