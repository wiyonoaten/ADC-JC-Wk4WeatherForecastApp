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

import androidx.annotation.DrawableRes
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.R
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.models.IconId

@DrawableRes fun IconId.toDrawableResId(): Int = when (this) {
    IconId.ClearSky -> R.drawable.ic_weather_01
    IconId.FewClouds -> R.drawable.ic_weather_02
    IconId.ScatteredClouds -> R.drawable.ic_weather_03
    IconId.BrokenClouds -> R.drawable.ic_weather_04
    IconId.ShowerRain -> R.drawable.ic_weather_09
    IconId.Rain -> R.drawable.ic_weather_10
    IconId.Thunderstorm -> R.drawable.ic_weather_11
    IconId.Snow -> R.drawable.ic_weather_13
    IconId.Mist -> R.drawable.ic_weather_50
}
