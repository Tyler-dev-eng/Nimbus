package ty.bre.nimbus.data.repository

import retrofit2.HttpException
import ty.bre.nimbus.data.mappers.toWeatherInfo
import ty.bre.nimbus.data.remote.WeatherApi
import ty.bre.nimbus.domain.repository.WeatherRepository
import ty.bre.nimbus.domain.util.Resource
import ty.bre.nimbus.domain.weather.WeatherInfo
import java.io.IOException
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherApi
) : WeatherRepository {
    override suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo> {
        return try {
            Resource.Success(
                data = api.getWeatherData(
                    lat = lat,
                    long = long
                ).toWeatherInfo()
            )
        } catch (e: IOException) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred.")
        } catch (e: HttpException) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred.")
        }
    }

}