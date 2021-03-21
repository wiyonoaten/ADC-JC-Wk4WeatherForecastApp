package com.wiyonoaten.composechallenge.wk4weatherforecastapp.viewmodels.types

import androidx.annotation.DrawableRes

data class HourlyForecastInfo(
    val time: String,
    @DrawableRes val iconResId: Int,
    val temperature: String,
    val chanceOfRainPct: Float
)
