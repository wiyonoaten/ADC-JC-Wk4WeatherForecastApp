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
