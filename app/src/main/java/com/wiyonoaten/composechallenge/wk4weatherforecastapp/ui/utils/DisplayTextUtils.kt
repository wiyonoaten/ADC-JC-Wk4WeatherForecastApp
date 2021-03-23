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
    "$value ${system.windSpeedUnit.toFormattedText()}"

fun SpeedUnit.toFormattedText() = when (this) {
    SpeedUnit.KilometresPerHour -> "km/h"
    SpeedUnit.MilesPerHour -> "mph"
}

fun Float.toFormattedRoundPercentageText() =
    "${this.times(100f).roundToInt()} %"
