package com.wiyonoaten.composechallenge.wk4weatherforecastapp.viewmodels.types

import com.wiyonoaten.composechallenge.wk4weatherforecastapp.models.Geolocation

sealed class LocationInfo(
    open val placeName: String,
    open val countryCode: String,
    open val geolocation: Geolocation
)

object UseCurrentLocation : LocationInfo("Current Location", "", Geolocation(0.0f, 0.0f))

data class FixedLocationInfo(
    override val placeName: String,
    override val countryCode: String,
    override val geolocation: Geolocation
) : LocationInfo(placeName, countryCode, geolocation)
