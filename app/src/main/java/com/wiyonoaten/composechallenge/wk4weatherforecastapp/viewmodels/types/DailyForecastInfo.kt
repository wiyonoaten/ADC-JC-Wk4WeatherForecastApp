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

import androidx.annotation.DrawableRes
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.models.Condition

data class DailyForecastInfo(
    val date: String,
    val condition: Condition,
    val description: String,
    @DrawableRes val iconResId: Int,
    val minTemperature: String,
    val maxTemperature: String,
    val mainTemperature: String,
    val windSpeed: String,
    val chanceOfRain: String,
    val hourly: List<HourlyForecastInfo>
)
