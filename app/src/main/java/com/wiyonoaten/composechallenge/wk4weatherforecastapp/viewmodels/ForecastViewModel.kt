package com.wiyonoaten.composechallenge.wk4weatherforecastapp.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.data.StubWeatherForecastRepository
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.data.WeatherForecastRepositoryImpl
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.viewmodels.types.DailyForecastInfo
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.models.Geolocation
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.models.MeasurementSystem
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.viewmodels.types.FixedLocationInfo
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.viewmodels.types.UseCurrentLocation
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.viewmodels.types.LocationInfo
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.viewmodels.types.toDailyForecastInfos
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

private const val USE_STUB_DATA = true

class ForecastViewModel(
    private val coroutineScope: CoroutineScope
) : ViewModel(), IForecastViewModel {

    private val repository = if (USE_STUB_DATA) StubWeatherForecastRepository() else WeatherForecastRepositoryImpl()

    private lateinit var dailyForecasts: List<DailyForecastInfo>

    // region States

    override var measurementSystem: MeasurementSystem by mutableStateOf(MeasurementSystem.values().first())
        private set

    override var isLoading: Boolean by mutableStateOf(false)
        private set

    override var selectedLocation: LocationInfo by mutableStateOf(UseCurrentLocation)
        private set

    override var availableLocations: List<LocationInfo> by mutableStateOf(listOf(UseCurrentLocation))
        private set

    override var availableForecastDates: List<String> by mutableStateOf(emptyList())
        private set

    override val selectedDateIndex: Int
        get() = selectedDailyForecast?.let { dailyForecasts.indexOf(it) } ?: 0

    override var selectedDailyForecast: DailyForecastInfo? by mutableStateOf(null)
        private set

    override val hasPreviousDay: Boolean
        get() = selectedDateIndex > 0

    override val hasNextDay: Boolean
        get() = selectedDateIndex < dailyForecasts.size - 1

    // endregion

    // region Events

    override fun onRefresh() {
        coroutineScope.launch {
            doRefresh()
        }
    }

    override fun onSelectLocation(location: LocationInfo) {
        coroutineScope.launch {
            selectedLocation = location
            doRefresh()
        }
    }

    override fun onSelectAnotherDay(isPreviousDay: Boolean) {
        val dateIndexToSelect = if (isPreviousDay) selectedDateIndex - 1 else selectedDateIndex + 1

        check(dateIndexToSelect in dailyForecasts.indices) {
            "Unexpected error due to date index out of bounds"
        }

        selectedDailyForecast = dailyForecasts[dateIndexToSelect]
    }

    // endregion

    init {
        measurementSystem = MeasurementSystem.Metric

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

        dailyForecasts = repository.loadMainForecast(selectedLocation.geolocation)
            .toDailyForecastInfos(measurementSystem)

        availableForecastDates = dailyForecasts.map { it.date }

        selectedDailyForecast = dailyForecasts.firstOrNull()

        isLoading = false
    }
}