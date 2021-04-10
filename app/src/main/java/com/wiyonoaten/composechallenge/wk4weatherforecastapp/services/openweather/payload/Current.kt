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
package com.wiyonoaten.composechallenge.wk4weatherforecastapp.services.openweather.payload

import com.google.gson.annotations.SerializedName

data class Current(
    val dt: Long,
    val temp: Float,
    val weather: List<Weather>,
    @SerializedName("wind_deg") val windDeg: Int,
    @SerializedName("wind_speed") val windSpeed: Float
)
