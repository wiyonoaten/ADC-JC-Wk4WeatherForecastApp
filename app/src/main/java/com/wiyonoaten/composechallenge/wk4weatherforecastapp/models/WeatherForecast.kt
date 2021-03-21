package com.wiyonoaten.composechallenge.wk4weatherforecastapp.models

data class WeatherForecast(
    val current: CurrentForecast,
    val daily: List<DailyForecast>
)
