package com.wiyonoaten.composechallenge.wk4weatherforecastapp.data

import com.wiyonoaten.composechallenge.wk4weatherforecastapp.models.WeatherForecast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.coroutines.suspendCoroutine

class StubWeatherForecastRepository : IWeatherForecastRepository {

    override suspend fun loadMainForecast(): WeatherForecast = suspendCoroutine { continuation ->
        CoroutineScope(continuation.context).launch {
            delay(300)
        }
    }
}