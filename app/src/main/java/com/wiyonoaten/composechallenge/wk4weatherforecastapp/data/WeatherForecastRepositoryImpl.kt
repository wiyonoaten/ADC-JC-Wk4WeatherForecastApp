package com.wiyonoaten.composechallenge.wk4weatherforecastapp.data

import com.wiyonoaten.composechallenge.wk4weatherforecastapp.models.Geolocation
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.models.WeatherForecast

class WeatherForecastRepositoryImpl : IWeatherForecastRepository {

    override suspend fun loadMainForecast(location: Geolocation): WeatherForecast {
        TODO("Not yet implemented")
    }
}
