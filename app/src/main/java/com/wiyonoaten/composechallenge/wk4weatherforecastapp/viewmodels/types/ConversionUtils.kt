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
package com.wiyonoaten.composechallenge.wk4weatherforecastapp.viewmodels.types

import com.wiyonoaten.composechallenge.wk4weatherforecastapp.models.MeasurementSystem
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.models.WeatherForecast
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.ui.utils.toDrawableResId
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.ui.utils.toFormattedRoundPercentageText
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.ui.utils.toFormattedText
import org.threeten.bp.format.DateTimeFormatter
import java.util.Locale

fun WeatherForecast.toDailyForecastInfos(
    system: MeasurementSystem
): List<DailyForecastInfo> = List(daily.size) { index ->
    with(daily[index]) {
        val isCurrentDay = index == 0
        DailyForecastInfo(
            date = if (isCurrentDay) {
                "Today, ${time.format(DateTimeFormatter.ofPattern("dd MMM", Locale.ENGLISH))}"
            } else {
                time.format(DateTimeFormatter.ofPattern("E, dd MMM", Locale.ENGLISH))
            },
            condition = if (isCurrentDay) current.condition else condition,
            description = if (isCurrentDay) current.description else description,
            iconResId = (if (isCurrentDay) current.iconId else iconId).toDrawableResId(),
            minTemperature = minTemperature.toFormattedText(system),
            maxTemperature = maxTemperature.toFormattedText(system),
            mainTemperature = (if (isCurrentDay) current.temperature else mainTemperature).toFormattedText(system),
            windSpeed = (if (isCurrentDay) current.windSpeed else windSpeed).toFormattedText(system),
            chanceOfRain = (if (isCurrentDay) current.chanceOfRain else chanceOfRain).toFormattedRoundPercentageText(),
            hourly = hourly.map {
                with(it) {
                    HourlyForecastInfo(
                        time.format(DateTimeFormatter.ofPattern("h a", Locale.ENGLISH)),
                        iconResId = iconId.toDrawableResId(),
                        temperature = temperature.toFormattedText(system),
                        chanceOfRainPct = chanceOfRain
                    )
                }
            }
        )
    }
}
