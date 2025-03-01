package ty.bre.nimbus.presentation

import ty.bre.nimbus.domain.weather.WeatherInfo

data class WeatherState(
    val weatherInfo: WeatherInfo? = null,
    val isLoading: Boolean = false,
    val locationName: String? = null,
    val error: String? = null
)