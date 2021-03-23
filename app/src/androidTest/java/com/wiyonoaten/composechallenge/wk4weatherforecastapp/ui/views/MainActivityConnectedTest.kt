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

import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.hasAnyDescendant
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.printToLog
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.ui.compositionlocals.LocalRunMode
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.ui.compositionlocals.RunMode
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.ui.theme.MyTheme
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.viewmodels.IForecastViewModel
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.viewmodels.sampledata.SAMPLE_AVAILABLE_FORECAST_DATES
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.viewmodels.sampledata.SAMPLE_AVAILABLE_LOCATIONS
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.viewmodels.sampledata.makeSampleDailyForecast
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.viewmodels.types.UseCurrentLocation
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class MainActivityConnectedTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var viewModel: IForecastViewModel

    @Before
    fun setup() {
        viewModel = mock() {
            on { isLoading } doReturn false
            on { availableLocations } doReturn SAMPLE_AVAILABLE_LOCATIONS
            on { selectedLocation } doReturn UseCurrentLocation
            on { availableForecastDates } doReturn SAMPLE_AVAILABLE_FORECAST_DATES
            on { selectedDateIndex } doReturn 0
            on { selectedDailyForecast } doReturn makeSampleDailyForecast(0)
            on { hasPreviousDay } doReturn false
            on { hasNextDay } doReturn true
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
    fun currentForecastHasCorrectIconAndDescription() {
        composeTestRule.onNodeWithContentDescription(
            label = "Current forecast is",
            substring = true,
            useUnmergedTree = true
        ).assert(
            hasAnyDescendant(hasText("Cloudy"))
        )
    }
}
