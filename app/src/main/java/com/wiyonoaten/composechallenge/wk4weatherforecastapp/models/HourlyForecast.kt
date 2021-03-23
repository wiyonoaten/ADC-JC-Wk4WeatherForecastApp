package com.wiyonoaten.composechallenge.wk4weatherforecastapp.models

import org.threeten.bp.OffsetDateTime

data class HourlyForecast(
    val time: OffsetDateTime,
    val iconId: IconId,
    val temperature: Temperature,
    val chanceOfRain: Float
)
