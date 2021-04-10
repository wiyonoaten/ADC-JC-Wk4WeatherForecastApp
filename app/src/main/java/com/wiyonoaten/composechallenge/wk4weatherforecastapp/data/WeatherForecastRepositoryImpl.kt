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
package com.wiyonoaten.composechallenge.wk4weatherforecastapp.data

import com.wiyonoaten.composechallenge.wk4weatherforecastapp.data.providers.IWeatherForecastProvider
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.data.providers.WeatherForecastProviderImpl
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.models.Geolocation
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.models.MeasurementSystem
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.models.WeatherForecast

class WeatherForecastRepositoryImpl : IWeatherForecastRepository {

    private val forecastProvider: IWeatherForecastProvider = WeatherForecastProviderImpl()

    override suspend fun loadMainForecast(location: Geolocation, measurementSystem: MeasurementSystem): WeatherForecast {
        return forecastProvider.fetchCurrentForecast(location, measurementSystem)
    }
}
