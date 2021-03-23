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
package com.wiyonoaten.composechallenge.wk4weatherforecastapp.ui.utils

import com.wiyonoaten.composechallenge.wk4weatherforecastapp.models.MeasurementSystem
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.models.SpeedUnit
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.models.Temperature
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.models.WindSpeed
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.viewmodels.types.LocationInfo
import kotlin.math.roundToInt

fun LocationInfo.toDisplayText() =
    "${placeName}${if (countryCode.isEmpty()) "" else ", $countryCode"}"

fun Temperature.toFormattedText(system: MeasurementSystem) =
    "${value.roundToInt()} °"

fun WindSpeed.toFormattedText(system: MeasurementSystem) =
    "${value.roundToInt()} ${system.windSpeedUnit.toFormattedText()}"

fun SpeedUnit.toFormattedText() = when (this) {
    SpeedUnit.KilometresPerHour -> "km/h"
    SpeedUnit.MilesPerHour -> "mph"
}

fun Float.toFormattedRoundPercentageText() =
    "${this.times(100f).roundToInt()} %"
