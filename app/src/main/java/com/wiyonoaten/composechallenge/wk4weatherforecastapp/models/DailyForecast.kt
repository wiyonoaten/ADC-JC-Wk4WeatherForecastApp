package com.wiyonoaten.composechallenge.wk4weatherforecastapp.models

import org.threeten.bp.OffsetDateTime

data class DailyForecast(
    val time: OffsetDateTime,
    val description: String,
    val iconId: String,
    val minTemperature: Temperature,
    val maxTemperature: Temperature,
    val mainTemperature: Temperature,
    val windSpeed: WindSpeed,
    val chanceOfRain: Float,
    val hourly: List<HourlyForecast>
)
