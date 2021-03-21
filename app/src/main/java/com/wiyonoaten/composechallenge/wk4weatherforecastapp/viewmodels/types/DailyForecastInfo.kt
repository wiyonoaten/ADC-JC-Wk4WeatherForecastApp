package com.wiyonoaten.composechallenge.wk4weatherforecastapp.viewmodels.types

import androidx.annotation.DrawableRes

data class DailyForecastInfo(
    val date: String,
    val description: String,
    @DrawableRes val iconResId: Int,
    val minTemperature: String,
    val maxTemperature: String,
    val mainTemperature: String,
    val windSpeed: String,
    val chanceOfRain: String,
    val hourly: List<HourlyForecastInfo>
)