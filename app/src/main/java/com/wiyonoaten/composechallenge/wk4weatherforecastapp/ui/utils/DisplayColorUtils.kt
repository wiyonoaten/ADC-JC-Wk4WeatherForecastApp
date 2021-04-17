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

import androidx.compose.ui.graphics.Color
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.models.Condition
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.ui.theme.blue200
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.ui.theme.blue400
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.ui.theme.cloudy200
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.ui.theme.cloudy400
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.ui.theme.foggy200
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.ui.theme.foggy400
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.ui.theme.snowy200
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.ui.theme.snowy400
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.ui.theme.sunny200
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.ui.theme.sunny400
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.ui.theme.thunder200
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.ui.theme.thunder400

fun Condition?.toBackgroundColorGradient(): Pair<Color, Color> =
    when (this) {
        Condition.Rain, Condition.Drizzle, Condition.Thunderstorm -> thunder200 to thunder400
        Condition.Snow -> snowy200 to snowy400
        Condition.Atmosphere, Condition.Haze -> foggy200 to foggy400
        Condition.Clear -> sunny200 to sunny400
        Condition.Clouds -> cloudy200 to cloudy400
        else -> blue200 to blue400
    }
