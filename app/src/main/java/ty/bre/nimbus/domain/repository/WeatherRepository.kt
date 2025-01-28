package ty.bre.nimbus.domain.repository

import ty.bre.nimbus.domain.util.Resource
import ty.bre.nimbus.domain.weather.WeatherInfo

interface WeatherRepository {
    suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo>
}