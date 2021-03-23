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
package com.wiyonoaten.composechallenge.wk4weatherforecastapp.ui.views

import android.app.Application
import android.content.res.Resources
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasAnyDescendant
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.printToLog
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.R
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.ui.compositionlocals.LocalRunMode
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.ui.compositionlocals.RunMode
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.ui.theme.MyTheme
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.viewmodels.ForecastViewModel
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.viewmodels.sampledata.SAMPLE_AVAILABLE_FORECAST_DATES
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.viewmodels.sampledata.SAMPLE_AVAILABLE_LOCATIONS
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.viewmodels.sampledata.makeSampleDailyForecast
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.viewmodels.types.LocationInfo
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class MainActivityConnectedTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var resources: Resources
    private lateinit var viewModel: ForecastViewModel

    @Before
    fun setup() {
        resources = ApplicationProvider.getApplicationContext<Application>().resources

        viewModel = object : ForecastViewModel() {

            init {
                doOnRefresh()
            }

            private fun doOnRefresh() {
                isLoading = true
                availableLocations = SAMPLE_AVAILABLE_LOCATIONS
                availableForecastDates = SAMPLE_AVAILABLE_FORECAST_DATES
                selectedDailyForecast = makeSampleDailyForecast(0).run {
                    copy(description = "$description - ${selectedLocation.placeName}")
                }
                isLoading = false
            }

            override val selectedDateIndex: Int
                get() = 0

            override val hasPreviousDay: Boolean
                get() = false

            override val hasNextDay: Boolean
                get() = true

            override fun onRefresh() {
                doOnRefresh()
            }

            override fun onSelectLocation(location: LocationInfo) {
                selectedLocation = location
                doOnRefresh()
            }

            override fun onSelectAnotherDay(isPreviousDay: Boolean) {
            }
        }

        composeTestRule.setContent {
            CompositionLocalProvider(LocalRunMode provides RunMode.InTesting) {
                MyTheme {
                    ActivityScreen(viewModel)
                }
            }
        }

        composeTestRule.onRoot().printToLog(MainActivityConnectedTest::class.java.simpleName)
    }

    @Test
    fun currentForecastHasCorrectDescription() {
        composeTestRule.onNodeWithContentDescription(
            label = "Current forecast is",
            substring = true,
            useUnmergedTree = true
        ).assert(
            hasAnyDescendant(hasText("Cloudy - Current Location"))
        )
    }

    @Test
    fun selectLocationWillUpdateForecastAccordingly() {
        composeTestRule.onNodeWithContentDescription(
            label = resources.getString(R.string.label_choose_another_location)
        ).performClick()

        composeTestRule.onNode(hasText("San Francisco, US") and hasClickAction())
            .assertIsDisplayed()
            .performClick()

        composeTestRule.onAllNodes(hasText("San Francisco, US"))
            .onFirst()
            .assertIsDisplayed()

        composeTestRule.onNode(hasText("Cloudy - San Francisco"))
            .assertIsDisplayed()
    }
}
