/*
 * Copyright 2021 Wiyono Aten
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.wiyonoaten.composechallenge.wk4weatherforecastapp.data.providers

import android.util.Log
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.models.CurrentForecast
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.models.DailyForecast
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.models.Geolocation
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.models.HourlyForecast
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.models.MeasurementSystem
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.models.Temperature
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.models.WeatherForecast
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.models.WindSpeed
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.services.openweather.API_KEY
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.services.openweather.IOpenWeatherService
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.services.openweather.openWeatherService
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.services.openweather.stubOpenWeatherService
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.services.openweather.toOpenWeatherUnits
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.services.openweather.toWeatherCondition
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.services.openweather.toWeatherIconId
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.utils.epochToOffsetDateTime

class WeatherForecastProviderImpl : IWeatherForecastProvider {

    private val owService: IOpenWeatherService = if (API_KEY.isBlank()) stubOpenWeatherService else openWeatherService

    override suspend fun fetchCurrentForecast(location: Geolocation, measurementSystem: MeasurementSystem): WeatherForecast {
        val oneCallResponse = owService.oneCallCurrentAndDaily(
            latitude = location.latitude,
            longitude = location.longitude,
            units = measurementSystem.toOpenWeatherUnits()
        )

        val fiveDayHourlyForecastResponse = owService.fiveDayHourlyForecast(
            latitude = location.latitude,
            longitude = location.longitude,
            units = measurementSystem.toOpenWeatherUnits()
        )

        Log.d("WeatherForecastProviderImpl", "oneCallResponse: $oneCallResponse")
        Log.d("WeatherForecastProviderImpl", "fiveDayHourlyForecastResponse: $fiveDayHourlyForecastResponse")

        var hourlyIndex = 0
        return WeatherForecast(
            current = with(oneCallResponse.current) {
                val weather = weather.first()
                CurrentForecast(
                    time = dt.epochToOffsetDateTime(),
                    condition = weather.main.toWeatherCondition(),
                    description = weather.description,
                    iconId = weather.icon.toWeatherIconId(),
                    temperature = Temperature(temp, measurementSystem.temperatureUnit),
                    windSpeed = WindSpeed(windSpeed, measurementSystem.windSpeedUnit, windDeg.toFloat()),
                    chanceOfRain = 0.0f
                )
            },
            daily = oneCallResponse.daily.take(5).mapIndexed { index, it ->
                with (it) {
                    val thisDayTime = dt.epochToOffsetDateTime()
                    val dailyWeather = weather.first()
                    val mainTemp = if (index == 0) oneCallResponse.current.temp else temp.day ?: temp.min
                    DailyForecast(
                        time = thisDayTime,
                        condition = dailyWeather.main.toWeatherCondition(),
                        description = dailyWeather.description,
                        iconId = dailyWeather.icon.toWeatherIconId(),
                        minTemperature = Temperature(temp.min, measurementSystem.temperatureUnit),
                        maxTemperature = Temperature(temp.max, measurementSystem.temperatureUnit),
                        mainTemperature = Temperature(mainTemp, measurementSystem.temperatureUnit),
                        windSpeed = WindSpeed(windSpeed, measurementSystem.windSpeedUnit, windDeg.toFloat()),
                        chanceOfRain = pop,
                        hourly = fiveDayHourlyForecastResponse.list
                            .takeLast(fiveDayHourlyForecastResponse.list.size - hourlyIndex)
                            .filter {
                                val thisHourlyTime = it.dt.epochToOffsetDateTime()
                                (thisHourlyTime.dayOfMonth == thisDayTime.dayOfMonth
                                        || (thisHourlyTime.dayOfMonth == thisDayTime.plusDays(1).dayOfMonth
                                            && thisHourlyTime.hour == 0 && thisHourlyTime.minute == 0 && thisHourlyTime.second == 0)).also { isFiltered ->
                                    if (isFiltered) ++hourlyIndex
                                }
                            }.map {
                                with (it) {
                                    val hourlyWeather = weather.first()
                                    HourlyForecast(
                                        time = dt.epochToOffsetDateTime(),
                                        iconId = hourlyWeather.icon.toWeatherIconId(),
                                        temperature = Temperature(main.main ?: main.min, measurementSystem.temperatureUnit),
                                        chanceOfRain = pop
                                    )
                                }
                            }
                    )
                }
            }
        )
    }
}
