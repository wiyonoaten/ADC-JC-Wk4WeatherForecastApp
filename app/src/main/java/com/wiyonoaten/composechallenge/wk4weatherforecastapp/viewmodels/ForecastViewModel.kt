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

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.models.MeasurementSystem
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.viewmodels.types.DailyForecastInfo
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.viewmodels.types.LocationInfo
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.viewmodels.types.UseCurrentLocation

abstract class ForecastViewModel : ViewModel() {

    // region States

    var measurementSystem: MeasurementSystem by mutableStateOf(MeasurementSystem.values().first())
        protected set

    var isLoading: Boolean by mutableStateOf(false)
        protected set

    var selectedLocation: LocationInfo by mutableStateOf(UseCurrentLocation)
        protected set

    var availableLocations: List<LocationInfo> by mutableStateOf(listOf(UseCurrentLocation))
        protected set

    var availableForecastDates: List<String> by mutableStateOf(emptyList())
        protected set

    abstract val selectedDateIndex: Int

    var selectedDailyForecast: DailyForecastInfo? by mutableStateOf(null)
        protected set

    abstract val hasPreviousDay: Boolean

    abstract val hasNextDay: Boolean

    // endregion

    // region Events

    abstract fun onRefresh()

    abstract fun onSelectLocation(location: LocationInfo)

    abstract fun onSelectAnotherDay(isPreviousDay: Boolean)

    // endregion
}
