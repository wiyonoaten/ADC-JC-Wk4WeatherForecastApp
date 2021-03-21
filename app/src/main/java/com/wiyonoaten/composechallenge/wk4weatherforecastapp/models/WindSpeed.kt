package com.wiyonoaten.composechallenge.wk4weatherforecastapp.models

enum class WindSpeedUnit {
    KilometresPerHour,
    MilesPerHour
}

data class WindSpeed(
    val value: Float,
    val unit: WindSpeedUnit,
    val degree: Float
)