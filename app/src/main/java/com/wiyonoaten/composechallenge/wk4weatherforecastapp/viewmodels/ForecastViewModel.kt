package com.wiyonoaten.composechallenge.wk4weatherforecastapp.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.data.WeatherForecastRepositoryImpl
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.viewmodels.types.DailyForecastInfo
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.models.Geolocation
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.viewmodels.types.FixedLocationInfo
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.viewmodels.types.UseCurrentLocation
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.viewmodels.types.LocationInfo
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.viewmodels.types.toDailyForecastInfos
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class ForecastViewModel(
    private val coroutineScope: CoroutineScope
) : ViewModel() {

    private val repository = WeatherForecastRepositoryImpl()

    private lateinit var dailyForecasts: List<DailyForecastInfo>

    // region States

    var isLoading: Boolean by mutableStateOf(false)
        private set

    var selectedLocation: LocationInfo by mutableStateOf(UseCurrentLocation)
        private set

    var availableLocations: List<LocationInfo> by mutableStateOf(listOf(UseCurrentLocation))
        private set

    var availableForecastDates: List<String> by mutableStateOf(emptyList())
        private set

    val selectedDateIndex: Int
        get() = selectedDailyForecast?.let { dailyForecasts.indexOf(it) } ?: 0

    var selectedDailyForecast: DailyForecastInfo? by mutableStateOf(null)
        private set

    val hasPreviousDay: Boolean
        get() = selectedDateIndex > 0

    val hasNextDay: Boolean
        get() = selectedDateIndex < dailyForecasts.size - 1

    // endregion

    // region Events

    fun onRefresh() {
        coroutineScope.launch {
            doRefresh()
        }
    }

    fun onSelectLocation(location: LocationInfo) {
        TODO()
    }

    fun onSelectAnotherDay(isPreviousDay: Boolean) {
        TODO()
    }

    // endregion

    init {
        availableLocations = listOf(
            UseCurrentLocation,
            FixedLocationInfo(
                placeName = "San Francisco",
                countryCode = "US",
                geolocation = Geolocation(37.773972f, -122.431297f)
            ),
            FixedLocationInfo(
                placeName = "London",
                countryCode = "UK",
                geolocation = Geolocation(51.509865f, -0.118092f)
            ),
            FixedLocationInfo(
                placeName = "New York City",
                countryCode = "US",
                geolocation = Geolocation(40.730610f, -73.935242f)
            ),
            FixedLocationInfo(
                placeName = "Jakarta",
                countryCode = "ID",
                geolocation = Geolocation(-6.200000f, 106.816666f)
            )
        )

        coroutineScope.launch {
            doRefresh()
        }
    }

    private suspend fun doRefresh() {
        isLoading = true
        dailyForecasts = repository.loadMainForecast().toDailyForecastInfos()
        selectedDailyForecast = dailyForecasts.firstOrNull()
        isLoading = false
    }
}