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
