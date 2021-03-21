package com.wiyonoaten.composechallenge.wk4weatherforecastapp.models

import org.threeten.bp.OffsetDateTime

data class CurrentForecast(
    val time: OffsetDateTime,
    val description: String,
    val iconId: String,
    val temperature: Temperature,
    val windSpeed: WindSpeed,
    val chanceOfRain: Float
)
