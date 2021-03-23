package com.wiyonoaten.composechallenge.wk4weatherforecastapp.viewmodels.sampledata

import com.wiyonoaten.composechallenge.wk4weatherforecastapp.R
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.models.Condition
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.models.Geolocation
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.viewmodels.types.DailyForecastInfo
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.viewmodels.types.FixedLocationInfo
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.viewmodels.types.HourlyForecastInfo
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.viewmodels.types.UseCurrentLocation

private val sanFrancisco = FixedLocationInfo(
    placeName = "San Francisco",
    countryCode = "US",
    geolocation = Geolocation(37.773972f, -122.431297f)
)

internal val SAMPLE_AVAILABLE_LOCATIONS = listOf(
    UseCurrentLocation,
    sanFrancisco,
)

internal val SAMPLE_AVAILABLE_FORECAST_DATES = listOf(
    "Today, 22 Mar",
    "Mon, 23 Mar",
    "Tue, 24 Mar",
    "Wed, 25 Mar",
    "Thu, 26 Mar",
)

internal fun makeSampleDailyForecast(selectedDateIndex: Int) = DailyForecastInfo(
    date = "Today, 22 Mar",
    condition = when (selectedDateIndex) {
        0 -> Condition.Clouds
        1 -> Condition.Clear
        2 -> Condition.Atmosphere
        3 -> Condition.Snow
        else -> Condition.Thunderstorm
    },
    description = when (selectedDateIndex) {
        0 -> "Cloudy"
        1 -> "Clear sky"
        2 -> "Haze"
        3 -> "Light snow shower"
        else -> "Heavy thunderstorm"
    },
    R.drawable.ic_weather_02,
    minTemperature = "10 °",
    maxTemperature = "35 °",
    mainTemperature = "${32 - selectedDateIndex} °",
    windSpeed = "8 km/h",
    chanceOfRain = "47 %",
    hourly = listOf(
        HourlyForecastInfo(
            time = "9 AM",
            R.drawable.ic_weather_02,
            temperature = "32 °",
            chanceOfRainPct = 62.5f
        ),
        HourlyForecastInfo(
            time = "12 PM",
            R.drawable.ic_weather_03,
            temperature = "31 °",
            chanceOfRainPct = 75.5f
        ),
        HourlyForecastInfo(
            time = "3 PM",
            R.drawable.ic_weather_03,
            temperature = "31 °",
            chanceOfRainPct = 55.0f
        ),
        HourlyForecastInfo(
            time = "6 PM",
            R.drawable.ic_weather_04,
            temperature = "30 °",
            chanceOfRainPct = 45.0f
        ),
        HourlyForecastInfo(
            time = "9 PM",
            R.drawable.ic_weather_02,
            temperature = "29 °",
            chanceOfRainPct = 68.0f
        ),
        HourlyForecastInfo(
            time = "12 AM",
            R.drawable.ic_weather_02,
            temperature = "26 °",
            chanceOfRainPct = 62.5f
        )
    )
)
