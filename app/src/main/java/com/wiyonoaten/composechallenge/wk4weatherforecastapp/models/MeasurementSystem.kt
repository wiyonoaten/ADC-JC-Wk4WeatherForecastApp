package com.wiyonoaten.composechallenge.wk4weatherforecastapp.models

enum class MeasurementSystem {
    Imperial,
    Metric;

    val temperatureUnit: TemperatureUnit
        get() = when(this) {
            Imperial -> TemperatureUnit.Fahrenheit
            Metric -> TemperatureUnit.Celcius
        }

    val windSpeedUnit: SpeedUnit
        get() = when(this) {
            Imperial -> SpeedUnit.MilesPerHour
            Metric -> SpeedUnit.KilometresPerHour
        }
}
