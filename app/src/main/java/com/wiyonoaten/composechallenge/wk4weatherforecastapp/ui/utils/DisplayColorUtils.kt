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

fun  Condition?.toBackgroundColorGradient(): Pair<Color, Color> =
    when (this) {
        Condition.Rain, Condition.Drizzle, Condition.Thunderstorm -> thunder200 to thunder400
        Condition.Snow -> snowy200 to snowy400
        Condition.Atmosphere -> foggy200 to foggy400
        Condition.Clear -> sunny200 to sunny400
        Condition.Clouds -> cloudy200 to cloudy400
        else -> blue200 to blue400
    }
