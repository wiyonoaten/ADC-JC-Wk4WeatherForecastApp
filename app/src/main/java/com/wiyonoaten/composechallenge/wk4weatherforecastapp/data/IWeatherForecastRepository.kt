package com.wiyonoaten.composechallenge.wk4weatherforecastapp.data

import com.wiyonoaten.composechallenge.wk4weatherforecastapp.models.WeatherForecast

interface IWeatherForecastRepository {

    suspend fun loadMainForecast(): WeatherForecast
}