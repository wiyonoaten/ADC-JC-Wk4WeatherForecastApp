package com.wiyonoaten.composechallenge.wk4weatherforecastapp.models

enum class TemperatureUnit {
    Celcius,
    Fahrenheit
}

data class Temperature(
    val value: Float,
    val unit: TemperatureUnit
)