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
package com.wiyonoaten.composechallenge.wk4weatherforecastapp.viewmodels

import com.wiyonoaten.composechallenge.wk4weatherforecastapp.models.MeasurementSystem
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.viewmodels.types.DailyForecastInfo
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.viewmodels.types.LocationInfo

interface IForecastViewModel {
    // States
    val measurementSystem: MeasurementSystem
    val isLoading: Boolean
    val selectedLocation: LocationInfo
    val availableLocations: List<LocationInfo>
    val availableForecastDates: List<String>
    val selectedDateIndex: Int
    val selectedDailyForecast: DailyForecastInfo?
    val hasPreviousDay: Boolean
    val hasNextDay: Boolean

    // Events
    fun onRefresh()
    fun onSelectLocation(location: LocationInfo)
    fun onSelectAnotherDay(isPreviousDay: Boolean)
}
