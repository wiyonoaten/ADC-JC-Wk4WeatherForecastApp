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
package com.wiyonoaten.composechallenge.wk4weatherforecastapp.services.openweather

import com.wiyonoaten.composechallenge.wk4weatherforecastapp.models.Condition
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.models.IconId
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.models.MeasurementSystem

fun MeasurementSystem.toOpenWeatherUnits() = when (this) {
    MeasurementSystem.Imperial -> "imperial"
    MeasurementSystem.Metric -> "metric"
}

fun String.toWeatherCondition() = Condition.valueOf(this)

fun String.toWeatherIconId() = when (this.trimEnd('d', 'n')) {
    "01" -> IconId.ClearSky
    "02" -> IconId.FewClouds
    "03" -> IconId.ScatteredClouds
    "04" -> IconId.BrokenClouds
    "09" -> IconId.ShowerRain
    "10" -> IconId.Rain
    "11" -> IconId.Thunderstorm
    "13" -> IconId.Snow
    "50" -> IconId.Mist
    else -> error("Unsupported weather icon id")
}
