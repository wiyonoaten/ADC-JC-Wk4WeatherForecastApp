package com.wiyonoaten.composechallenge.wk4weatherforecastapp.data

import com.wiyonoaten.composechallenge.wk4weatherforecastapp.models.Condition
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.models.CurrentForecast
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.models.DailyForecast
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.models.Geolocation
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.models.HourlyForecast
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.models.IconId
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.models.Temperature
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.models.TemperatureUnit
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.models.WeatherForecast
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.models.WindSpeed
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.models.SpeedUnit
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.utils.epochToOffsetDateTime
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class StubWeatherForecastRepository : IWeatherForecastRepository {

    override suspend fun loadMainForecast(location: Geolocation): WeatherForecast = suspendCoroutine { continuation ->
        CoroutineScope(continuation.context).launch {
            delay(1000)

            continuation.resume(WeatherForecast(
                current = CurrentForecast(
                    1616408284L.epochToOffsetDateTime(),
                    condition = Condition.Clouds,
                    description = "Cloudy",
                    iconId = IconId.BrokenClouds,
                    temperature = Temperature(32.0f, TemperatureUnit.Celcius),
                    windSpeed = WindSpeed(8.0f, SpeedUnit.KilometresPerHour, 8.0f),
                    chanceOfRain = 0.47f
                ),
                listOf(
                    DailyForecast(
                        time = 1616403600L.epochToOffsetDateTime(),
                        condition = Condition.Clouds,
                        description = "Cloudy",
                        iconId = IconId.BrokenClouds,
                        minTemperature = Temperature(10.0f, TemperatureUnit.Celcius),
                        maxTemperature = Temperature(35.0f, TemperatureUnit.Celcius),
                        mainTemperature = Temperature(32.0f, TemperatureUnit.Celcius),
                        windSpeed = WindSpeed(8.0f, SpeedUnit.KilometresPerHour, 8.0f),
                        chanceOfRain = 0.47f,
                        hourly = listOf(
                            HourlyForecast(
                                time = 1616403600L.epochToOffsetDateTime(),
                                iconId = IconId.BrokenClouds,
                                temperature = Temperature(32.0f, TemperatureUnit.Celcius),
                                chanceOfRain = 62.5f
                            ),
                            HourlyForecast(
                                time = 1616414400L.epochToOffsetDateTime(),
                                iconId = IconId.FewClouds,
                                temperature = Temperature(31.0f, TemperatureUnit.Celcius),
                                chanceOfRain = 75.5f
                            ),
                            HourlyForecast(
                                time = 1616425200L.epochToOffsetDateTime(),
                                iconId = IconId.ClearSky,
                                temperature = Temperature(31.0f, TemperatureUnit.Celcius),
                                chanceOfRain = 55.5f
                            ),
                            HourlyForecast(
                                time = 1616436000L.epochToOffsetDateTime(),
                                iconId = IconId.Mist,
                                temperature = Temperature(30.0f, TemperatureUnit.Celcius),
                                chanceOfRain = 45.5f
                            ),
                            HourlyForecast(
                                time = 1616446800L.epochToOffsetDateTime(),
                                iconId = IconId.ShowerRain,
                                temperature = Temperature(29.0f, TemperatureUnit.Celcius),
                                chanceOfRain = 68.0f
                            ),
                            HourlyForecast(
                                time = 1616457600L.epochToOffsetDateTime(),
                                iconId = IconId.Rain,
                                temperature = Temperature(26.0f, TemperatureUnit.Celcius),
                                chanceOfRain = 62.5f
                            )
                        )
                    ),
                    DailyForecast(
                        time = 1616490000L.epochToOffsetDateTime(),
                        condition = Condition.Drizzle,
                        description = "Shower drizzle",
                        iconId = IconId.ShowerRain,
                        minTemperature = Temperature(10.0f, TemperatureUnit.Celcius),
                        maxTemperature = Temperature(35.0f, TemperatureUnit.Celcius),
                        mainTemperature = Temperature(32.0f, TemperatureUnit.Celcius),
                        windSpeed = WindSpeed(8.0f, SpeedUnit.KilometresPerHour, 8.0f),
                        chanceOfRain = 0.47f,
                        hourly = listOf(
                            HourlyForecast(
                                time = 1616403600L.epochToOffsetDateTime(),
                                iconId = IconId.BrokenClouds,
                                temperature = Temperature(32.0f, TemperatureUnit.Celcius),
                                chanceOfRain = 62.5f
                            ),
                            HourlyForecast(
                                time = 1616414400L.epochToOffsetDateTime(),
                                iconId = IconId.FewClouds,
                                temperature = Temperature(31.0f, TemperatureUnit.Celcius),
                                chanceOfRain = 75.5f
                            ),
                            HourlyForecast(
                                time = 1616425200L.epochToOffsetDateTime(),
                                iconId = IconId.ClearSky,
                                temperature = Temperature(31.0f, TemperatureUnit.Celcius),
                                chanceOfRain = 55.5f
                            ),
                            HourlyForecast(
                                time = 1616436000L.epochToOffsetDateTime(),
                                iconId = IconId.Mist,
                                temperature = Temperature(30.0f, TemperatureUnit.Celcius),
                                chanceOfRain = 45.5f
                            ),
                            HourlyForecast(
                                time = 1616446800L.epochToOffsetDateTime(),
                                iconId = IconId.ShowerRain,
                                temperature = Temperature(29.0f, TemperatureUnit.Celcius),
                                chanceOfRain = 68.0f
                            ),
                            HourlyForecast(
                                time = 1616457600L.epochToOffsetDateTime(),
                                iconId = IconId.Rain,
                                temperature = Temperature(26.0f, TemperatureUnit.Celcius),
                                chanceOfRain = 62.5f
                            )
                        )
                    ),
                    DailyForecast(
                        time = 1616576400L.epochToOffsetDateTime(),
                        condition = Condition.Clear,
                        description = "Clear sky",
                        iconId = IconId.ClearSky,
                        minTemperature = Temperature(10.0f, TemperatureUnit.Celcius),
                        maxTemperature = Temperature(35.0f, TemperatureUnit.Celcius),
                        mainTemperature = Temperature(32.0f, TemperatureUnit.Celcius),
                        windSpeed = WindSpeed(8.0f, SpeedUnit.KilometresPerHour, 8.0f),
                        chanceOfRain = 0.47f,
                        hourly = listOf(
                            HourlyForecast(
                                time = 1616403600L.epochToOffsetDateTime(),
                                iconId = IconId.BrokenClouds,
                                temperature = Temperature(32.0f, TemperatureUnit.Celcius),
                                chanceOfRain = 62.5f
                            ),
                            HourlyForecast(
                                time = 1616414400L.epochToOffsetDateTime(),
                                iconId = IconId.FewClouds,
                                temperature = Temperature(31.0f, TemperatureUnit.Celcius),
                                chanceOfRain = 75.5f
                            ),
                            HourlyForecast(
                                time = 1616425200L.epochToOffsetDateTime(),
                                iconId = IconId.ClearSky,
                                temperature = Temperature(31.0f, TemperatureUnit.Celcius),
                                chanceOfRain = 55.5f
                            ),
                            HourlyForecast(
                                time = 1616436000L.epochToOffsetDateTime(),
                                iconId = IconId.Mist,
                                temperature = Temperature(30.0f, TemperatureUnit.Celcius),
                                chanceOfRain = 45.5f
                            ),
                            HourlyForecast(
                                time = 1616446800L.epochToOffsetDateTime(),
                                iconId = IconId.ShowerRain,
                                temperature = Temperature(29.0f, TemperatureUnit.Celcius),
                                chanceOfRain = 68.0f
                            ),
                            HourlyForecast(
                                time = 1616457600L.epochToOffsetDateTime(),
                                iconId = IconId.Rain,
                                temperature = Temperature(26.0f, TemperatureUnit.Celcius),
                                chanceOfRain = 62.5f
                            )
                        )
                    ),
                    DailyForecast(
                        time = 1616662800L.epochToOffsetDateTime(),
                        condition = Condition.Thunderstorm,
                        description = "Heavy thunderstorm",
                        iconId = IconId.Thunderstorm,
                        minTemperature = Temperature(10.0f, TemperatureUnit.Celcius),
                        maxTemperature = Temperature(35.0f, TemperatureUnit.Celcius),
                        mainTemperature = Temperature(32.0f, TemperatureUnit.Celcius),
                        windSpeed = WindSpeed(8.0f, SpeedUnit.KilometresPerHour, 8.0f),
                        chanceOfRain = 0.47f,
                        hourly = listOf(
                            HourlyForecast(
                                time = 1616403600L.epochToOffsetDateTime(),
                                iconId = IconId.BrokenClouds,
                                temperature = Temperature(32.0f, TemperatureUnit.Celcius),
                                chanceOfRain = 62.5f
                            ),
                            HourlyForecast(
                                time = 1616414400L.epochToOffsetDateTime(),
                                iconId = IconId.FewClouds,
                                temperature = Temperature(31.0f, TemperatureUnit.Celcius),
                                chanceOfRain = 75.5f
                            ),
                            HourlyForecast(
                                time = 1616425200L.epochToOffsetDateTime(),
                                iconId = IconId.ClearSky,
                                temperature = Temperature(31.0f, TemperatureUnit.Celcius),
                                chanceOfRain = 55.5f
                            ),
                            HourlyForecast(
                                time = 1616436000L.epochToOffsetDateTime(),
                                iconId = IconId.Mist,
                                temperature = Temperature(30.0f, TemperatureUnit.Celcius),
                                chanceOfRain = 45.5f
                            ),
                            HourlyForecast(
                                time = 1616446800L.epochToOffsetDateTime(),
                                iconId = IconId.ShowerRain,
                                temperature = Temperature(29.0f, TemperatureUnit.Celcius),
                                chanceOfRain = 68.0f
                            ),
                            HourlyForecast(
                                time = 1616457600L.epochToOffsetDateTime(),
                                iconId = IconId.Rain,
                                temperature = Temperature(26.0f, TemperatureUnit.Celcius),
                                chanceOfRain = 62.5f
                            )
                        )
                    ),
                    DailyForecast(
                        time = 1616749200L.epochToOffsetDateTime(),
                        condition = Condition.Atmosphere,
                        description = "Haze",
                        iconId = IconId.Mist,
                        minTemperature = Temperature(10.0f, TemperatureUnit.Celcius),
                        maxTemperature = Temperature(35.0f, TemperatureUnit.Celcius),
                        mainTemperature = Temperature(32.0f, TemperatureUnit.Celcius),
                        windSpeed = WindSpeed(8.0f, SpeedUnit.KilometresPerHour, 8.0f),
                        chanceOfRain = 0.47f,
                        hourly = listOf(
                            HourlyForecast(
                                time = 1616403600L.epochToOffsetDateTime(),
                                iconId = IconId.BrokenClouds,
                                temperature = Temperature(32.0f, TemperatureUnit.Celcius),
                                chanceOfRain = 62.5f
                            ),
                            HourlyForecast(
                                time = 1616414400L.epochToOffsetDateTime(),
                                iconId = IconId.FewClouds,
                                temperature = Temperature(31.0f, TemperatureUnit.Celcius),
                                chanceOfRain = 75.5f
                            ),
                            HourlyForecast(
                                time = 1616425200L.epochToOffsetDateTime(),
                                iconId = IconId.ClearSky,
                                temperature = Temperature(31.0f, TemperatureUnit.Celcius),
                                chanceOfRain = 55.5f
                            ),
                            HourlyForecast(
                                time = 1616436000L.epochToOffsetDateTime(),
                                iconId = IconId.Mist,
                                temperature = Temperature(30.0f, TemperatureUnit.Celcius),
                                chanceOfRain = 45.5f
                            ),
                            HourlyForecast(
                                time = 1616446800L.epochToOffsetDateTime(),
                                iconId = IconId.ShowerRain,
                                temperature = Temperature(29.0f, TemperatureUnit.Celcius),
                                chanceOfRain = 68.0f
                            ),
                            HourlyForecast(
                                time = 1616457600L.epochToOffsetDateTime(),
                                iconId = IconId.Rain,
                                temperature = Temperature(26.0f, TemperatureUnit.Celcius),
                                chanceOfRain = 62.5f
                            )
                        )
                    )
                )
            ))
        }
    }
}
