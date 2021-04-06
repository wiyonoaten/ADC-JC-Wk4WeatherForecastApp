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

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.annotation.VisibleForTesting
import androidx.annotation.VisibleForTesting.PRIVATE
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntOffset
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.outlined.Grain
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material.icons.outlined.Speed
import androidx.compose.material.rememberSwipeableState
import androidx.compose.material.swipeable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect.Companion.dashPathEffect
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.R
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.ui.compositionlocals.LocalRunMode
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.ui.compositionlocals.RunMode
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.ui.modifiers.rememberLayoutSize
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.ui.theme.MyTheme
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.ui.theme.amber200
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.ui.theme.gray100
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.ui.theme.gray200
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.ui.theme.gray400
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.ui.utils.toBackgroundColorGradient
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.ui.utils.toDisplayText
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.viewmodels.ForecastViewModel
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.viewmodels.ForecastViewModelImpl
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.viewmodels.sampledata.SAMPLE_AVAILABLE_FORECAST_DATES
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.viewmodels.sampledata.SAMPLE_AVAILABLE_LOCATIONS
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.viewmodels.sampledata.makeSampleDailyForecast
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.viewmodels.types.DailyForecastInfo
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.viewmodels.types.HourlyForecastInfo
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.viewmodels.types.LocationInfo
import dev.chrisbanes.accompanist.insets.ProvideWindowInsets
import dev.chrisbanes.accompanist.insets.statusBarsHeight
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue
import kotlin.math.roundToInt

class MainActivity : BaseActivity() {
    private lateinit var viewModel: ForecastViewModelImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(
            this,
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel?> create(modelClass: Class<T>): T =
                    ForecastViewModelImpl(lifecycleScope) as T
            }
        ).get(ForecastViewModelImpl::class.java)

        setContent {
            MyTheme {
                ActivityScreen(viewModel)
            }
        }
    }
}

@VisibleForTesting(otherwise = PRIVATE)
@Composable
internal fun ActivityScreen(
    viewModel: ForecastViewModel
) {
    with(viewModel) {
        val hasPreviousDayState = remember { derivedStateOf { viewModel.hasPreviousDay } }
        val hasNextDayState = remember { derivedStateOf { viewModel.hasNextDay } }

        ForecastDashboard(
            isLoading = isLoading,
            availableLocations = availableLocations,
            selectedLocation = selectedLocation,
            onSelectLocation = ::onSelectLocation,
            availableForecastDates = availableForecastDates,
            selectedDateIndex = selectedDateIndex,
            selectedDailyForecast = selectedDailyForecast,
            hasPreviousDayState = hasPreviousDayState,
            hasNextDayState = hasNextDayState,
            onSelectAnotherDay = ::onSelectAnotherDay,
            onRefresh = ::onRefresh
        )
    }
}

@Composable
private fun ForecastDashboard(
    isLoading: Boolean,
    availableLocations: List<LocationInfo>,
    selectedLocation: LocationInfo,
    onSelectLocation: (LocationInfo) -> Unit,
    availableForecastDates: List<String>,
    selectedDateIndex: Int,
    selectedDailyForecast: DailyForecastInfo?,
    hasPreviousDayState: State<Boolean>,
    hasNextDayState: State<Boolean>,
    onSelectAnotherDay: (Boolean) -> Unit,
    onRefresh: () -> Unit
) {
    val backgroundColorGradient: Pair<Color, Color> = selectedDailyForecast?.condition.toBackgroundColorGradient()
    val backgroundColorAnimationSpec = tween<Color>(durationMillis = 500, easing = LinearEasing)

    val backgroundColorTop by animateColorAsState(
        targetValue = backgroundColorGradient.first,
        animationSpec = backgroundColorAnimationSpec
    )
    val backgroundColorBottom by animateColorAsState(
        targetValue = backgroundColorGradient.second,
        animationSpec = backgroundColorAnimationSpec
    )

    ProvideWindowInsets {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .background(Brush.verticalGradient(listOf(backgroundColorTop, backgroundColorBottom)))
            ) {
                Spacer(modifier = Modifier.statusBarsHeight(24.dp))
                LocationSelector(
                    isLoading = isLoading,
                    availableLocations = availableLocations,
                    selectedLocation = selectedLocation,
                    onSelectLocation = onSelectLocation,
                    onRefresh = onRefresh,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                if (isLoading || selectedDailyForecast == null) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(modifier = Modifier.size(48.dp))
                    }
                } else {
                    ForecastView(
                        availableForecastDates = availableForecastDates,
                        selectedDateIndex = selectedDateIndex,
                        selectedDailyForecast = selectedDailyForecast,
                        hasPreviousDayState = hasPreviousDayState,
                        hasNextDayState = hasNextDayState,
                        onSelectAnotherDay = onSelectAnotherDay
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun LocationSelector(
    isLoading: Boolean,
    availableLocations: List<LocationInfo>,
    selectedLocation: LocationInfo,
    onSelectLocation: (LocationInfo) -> Unit,
    onRefresh: () -> Unit,
    modifier: Modifier = Modifier
) {
    var isDropdownActive by remember { mutableStateOf(false) }

    Column(modifier) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .offset(x = (-12).dp)
        ) {
            Icon(
                imageVector = Icons.Filled.LocationOn,
                contentDescription = stringResource(R.string.label_location),
                modifier = Modifier
                    .size(48.dp)
                    .padding(12.dp)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .height(48.dp)
                    .clickable {
                        isDropdownActive = !isDropdownActive
                    }
            ) {
                Spacer(modifier = Modifier.size(12.dp))
                Text(
                    text = selectedLocation.toDisplayText(),
                    style = MaterialTheme.typography.h4
                )
                Spacer(modifier = Modifier.size(20.dp))
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowDown,
                    contentDescription = stringResource(R.string.label_choose_another_location),
                    modifier = Modifier.size(24.dp)
                )
            }
            Spacer(modifier = Modifier.weight(1.0f))
            Icon(
                imageVector = Icons.Outlined.Refresh,
                contentDescription = stringResource(R.string.label_refresh),
                modifier = Modifier
                    .offset(x = 24.dp)
                    .size(48.dp)
                    .clickable { onRefresh.invoke() }
                    .padding(12.dp)
            )
        }
        AnimatedVisibility(visible = isDropdownActive) {
            DropdownMenu(
                expanded = true,
                offset = DpOffset(36.dp, 0.dp),
                onDismissRequest = {
                    isDropdownActive = false
                },
                modifier = Modifier.background(Color.White.copy(alpha = 0.5f))
            ) {
                availableLocations.forEach {
                    DropdownMenuItem(
                        onClick = {
                            onSelectLocation.invoke(it)
                            isDropdownActive = false
                        }
                    ) {
                        Text(
                            text = it.toDisplayText(),
                            style = MaterialTheme.typography.h4
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun ForecastView(
    availableForecastDates: List<String>,
    selectedDateIndex: Int,
    selectedDailyForecast: DailyForecastInfo,
    hasPreviousDayState: State<Boolean>,
    hasNextDayState: State<Boolean>,
    onSelectAnotherDay: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val hasPreviousDay by hasPreviousDayState
    val hasNextDay by hasNextDayState

    val coroutineScope = rememberCoroutineScope()

    val swipeableState = rememberSwipeableState(0) {
        val isConfirmed = when (it) {
            -1 -> if (hasPreviousDay) { onSelectAnotherDay(true).let { true } } else false
            1 -> if (hasNextDay) { onSelectAnotherDay(false).let { true } } else false
            else -> true
        }
        isConfirmed
    }
    var isSwipingEnabled by remember { mutableStateOf(true) }

    var prevSelectedDateIndex by remember { mutableStateOf(0) }

    if (prevSelectedDateIndex != selectedDateIndex) {
        coroutineScope.launch {
            isSwipingEnabled = false
            swipeableState.snapTo(if (prevSelectedDateIndex < selectedDateIndex) -1 else 1)
            prevSelectedDateIndex = selectedDateIndex
            swipeableState.animateTo(0)
            isSwipingEnabled = true
        }
    }

    var containerWidth by remember { mutableStateOf(1) }

    Box(
        modifier = modifier
            .offset { IntOffset(swipeableState.offset.value.roundToInt(), 0) }
            .fillMaxSize()
            .rememberLayoutSize {
                containerWidth = it.width
            }
            .alpha(
                swipeableState.offset.value.let { swipeOffset ->
                    when {
                        swipeOffset.isNaN() -> 1.0f
                        else -> 1.0f - swipeOffset.absoluteValue / containerWidth
                            .toFloat()
                            .div(2f)
                    }
                }
            )
            .padding(start = 16.dp, top = 12.dp, bottom = 12.dp)
            .swipeable(
                swipeableState,
                enabled = isSwipingEnabled,
                anchors = mutableMapOf<Float, Int>().apply {
                    put(
                        containerWidth
                            .toFloat()
                            .div(2f), -1
                    )
                    put(0f, 0)
                    put(
                        -containerWidth
                            .toFloat()
                            .div(2f), 1
                    )
                },
                orientation = Orientation.Horizontal,
                thresholds = { _, _ -> FractionalThreshold(0.3f) }
            )
    ) {
        Column(
            modifier = modifier
                .align(Alignment.CenterStart)
                .verticalScroll(rememberScrollState())
        ) {
            DayForecastPanel(selectedDailyForecast, modifier = Modifier.padding(end = 16.dp))
            Spacer(modifier = Modifier.size(40.dp))
            DatesPanel(availableForecastDates, selectedDateIndex)
            Spacer(modifier = Modifier.size(16.dp))
            HourlyForecastPanel(selectedDateIndex, selectedDailyForecast.hourly)
            ChanceOfRainPanel(
                isCurrentDate = selectedDateIndex == 0,
                hourlyForecast = selectedDailyForecast.hourly.take(6),
                modifier = Modifier.padding(end = 16.dp)
            )
        }
    }
}

@Composable
private fun DayForecastPanel(
    forecastInfo: DailyForecastInfo,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
    ) {
        with(forecastInfo) {
            val headingDescriptionText = stringResource(R.string.description_heading_current_forecast_fmt, description)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.semantics(mergeDescendants = true) {
                    heading()
                    contentDescription = headingDescriptionText
                }
            ) {
                Icon(
                    painter = painterResource(id = iconResId),
                    contentDescription = null,
                    modifier = Modifier.size(32.dp)
                )
                Spacer(modifier = Modifier.size(4.dp))
                Text(
                    maxLines = 1,
                    text = description,
                    style = MaterialTheme.typography.h5,
                    color = gray200
                )
            }
            Spacer(modifier = Modifier.size(4.dp))
            Text(
                maxLines = 1,
                text = mainTemperature,
                style = MaterialTheme.typography.h1,
                color = gray400
            )
            Spacer(modifier = Modifier.size(4.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                val windSpeedContentDescription = stringResource(R.string.description_wind_speed_fmt, windSpeed)
                Icon(
                    imageVector = Icons.Outlined.Speed,
                    tint = gray100,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.size(4.dp))
                Text(
                    maxLines = 1,
                    text = windSpeed,
                    style = MaterialTheme.typography.subtitle1,
                    color = gray100,
                    modifier = Modifier.semantics { contentDescription = windSpeedContentDescription }
                )
                Spacer(modifier = Modifier.size(16.dp))
                val corContentDescription = stringResource(R.string.description_chance_of_rain_fmt, chanceOfRain)
                Icon(
                    imageVector = Icons.Outlined.Grain,
                    tint = gray100,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.size(4.dp))
                Text(
                    maxLines = 1,
                    text = chanceOfRain,
                    style = MaterialTheme.typography.subtitle1,
                    color = gray100,
                    modifier = Modifier.semantics { contentDescription = corContentDescription }
                )
            }
        }
    }
}

@Composable
private fun DatesPanel(
    availableForecastDates: List<String>,
    selectedDateIndex: Int,
    modifier: Modifier = Modifier
) {
    // Q for Jetpack team: Why is the use of rememberLayoutSize modifier inside Row here somehow not working, when run
    // in instrumented test (only), ie. will cause test to hang, seemingly indefinitely waiting on idle.
    val isInTestingMode = LocalRunMode.current == RunMode.InTesting

    val coroutineScope = rememberCoroutineScope()

    val itemWidths = remember {
        mutableStateListOf(
            *Array(availableForecastDates.size) {
                if (isInTestingMode) 100 else 0
            }
        )
    }

    val scrollState = rememberScrollState()

    var prevSelectedDateIndex by remember { mutableStateOf(0) }

    if (selectedDateIndex > prevSelectedDateIndex) {
        if (selectedDateIndex > 2) {
            coroutineScope.launch {
                val scrollPosition = itemWidths.take(selectedDateIndex - 2).sum()
                scrollState.scrollTo(scrollPosition)
                prevSelectedDateIndex = selectedDateIndex
            }
        } else {
            prevSelectedDateIndex = selectedDateIndex
        }
    } else if (selectedDateIndex < prevSelectedDateIndex) {
        val selectedItemScrollPosition = itemWidths.take(selectedDateIndex).sum()
        if (scrollState.value > selectedItemScrollPosition) {
            coroutineScope.launch {
                scrollState.scrollTo(selectedItemScrollPosition)
                prevSelectedDateIndex = selectedDateIndex
            }
        } else {
            prevSelectedDateIndex = selectedDateIndex
        }
    }

    val headingDescriptionText = stringResource(R.string.description_heading_dates_selection)
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .horizontalScroll(scrollState, enabled = false)
            .semantics {
                heading()
                contentDescription = headingDescriptionText
            }
    ) {
        availableForecastDates.forEachIndexed { index, it ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .run {
                        if (isInTestingMode) this else {
                            rememberLayoutSize { itemWidths[index] = it.width }
                        }
                    }
            ) {
                Text(
                    maxLines = 1,
                    text = it,
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.alpha(if (selectedDateIndex == index) 1.0f else 0.5f)
                )
                Spacer(modifier = Modifier.size(if (index < availableForecastDates.size - 1) 40.dp else 16.dp))
            }
        }
    }
}

@Composable
private fun HourlyForecastPanel(
    selectedDateIndex: Int,
    hourlyForecast: List<HourlyForecastInfo>,
    modifier: Modifier = Modifier
) {
    val coroutineScope = rememberCoroutineScope()

    val lazyListState = rememberLazyListState()

    var needStartAnimation by remember { mutableStateOf(true) }

    var prevSelectedDateIndex by remember { mutableStateOf(0) }

    if (prevSelectedDateIndex != selectedDateIndex) {
        coroutineScope.launch {
            lazyListState.scrollToItem(0)
            prevSelectedDateIndex = selectedDateIndex
            needStartAnimation = true
        }
    }

    LazyRow(
        state = lazyListState,
        modifier = modifier
            .fillMaxWidth()
    ) {
        items(hourlyForecast.size) { index ->
            with(hourlyForecast[index]) {
                val startAnimationTransition = updateTransition(targetState = needStartAnimation)

                val animatedOffset by startAnimationTransition.animateIntOffset(
                    transitionSpec = {
                        tween(75, index * 60, easing = FastOutLinearInEasing)
                    }
                ) {
                    if (needStartAnimation) IntOffset(0, 32) else IntOffset(0, 0)
                }

                val animatedAlpha by startAnimationTransition.animateFloat(
                    transitionSpec = {
                        tween(75, index * 60, easing = FastOutLinearInEasing)
                    }
                ) {
                    if (needStartAnimation) 0.5f else 1.0f
                }

                if (startAnimationTransition.currentState == startAnimationTransition.targetState
                    && index == 2.coerceAtMost(hourlyForecast.lastIndex)) {
                    needStartAnimation = false
                }

                val contentDescriptionText = stringResource(R.string.description_hourly_forecast_fmt, time, temperature)
                Column {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .offset { with(animatedOffset) { IntOffset(x.dp.roundToPx(), y.dp.roundToPx()) } }
                            .alpha(animatedAlpha)
                            .padding(end = 20.dp)
                            .clip(MaterialTheme.shapes.medium)
                            .background(Color.White.copy(alpha = 0.25f))
                            .padding(horizontal = 32.dp, vertical = 16.dp)
                            .semantics(mergeDescendants = true) {
                                contentDescription = contentDescriptionText
                            }
                    ) {
                        Text(
                            maxLines = 1,
                            text = time,
                            style = MaterialTheme.typography.body2,
                            color = gray100
                        )
                        Spacer(modifier = Modifier.size(8.dp))
                        Icon(
                            painter = painterResource(id = iconResId),
                            contentDescription = null,
                            modifier = Modifier.size(64.dp)
                        )
                        Spacer(modifier = Modifier.size(8.dp))
                        Text(
                            maxLines = 1,
                            text = temperature,
                            style = MaterialTheme.typography.h3,
                            color = gray400
                        )
                    }
                    Spacer(modifier = Modifier.size(32.dp))
                }
            }
        }
    }
}

@Composable
private fun ChanceOfRainPanel(
    isCurrentDate: Boolean,
    hourlyForecast: List<HourlyForecastInfo>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            maxLines = 1,
            text = "Chance of rain",
            style = MaterialTheme.typography.h6,
            modifier = Modifier.semantics { heading() }
        )
        Spacer(modifier = Modifier.size(16.dp))
        Row(
            verticalAlignment = Alignment.Top,
            modifier = Modifier.height(120.dp)
        ) {
            CorRainyAxisLabels(
                modifier = Modifier
                    .width(48.dp)
                    .fillMaxHeight()
            )
            CorChart(
                isCurrentDate = isCurrentDate,
                hourlyForecast = hourlyForecast,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Composable
private fun CorRainyAxisLabels(
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = modifier.clearAndSetSemantics { }
    ) {
        Box(
            contentAlignment = Alignment.TopStart,
            modifier = Modifier.weight(1.0f)
        ) {
            Text(
                maxLines = 2,
                text = "sunny",
                style = MaterialTheme.typography.caption
            )
        }
        Box(
            contentAlignment = Alignment.TopStart,
            modifier = Modifier.weight(1.0f)
        ) {
            Text(
                maxLines = 2,
                text = "rainy",
                style = MaterialTheme.typography.caption
            )
        }
        Box(
            contentAlignment = Alignment.TopStart,
            modifier = Modifier.weight(1.0f)
        ) {
            Text(
                maxLines = 2,
                text = "heavy rain",
                style = MaterialTheme.typography.caption
            )
        }
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            maxLines = 1,
            text = "99AM",
            style = MaterialTheme.typography.body2,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .alpha(0.0f)
                .clearAndSetSemantics { }
        )
    }
}

@Composable
private fun CorChart(
    isCurrentDate: Boolean,
    hourlyForecast: List<HourlyForecastInfo>,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = if (hourlyForecast.size > 2) Arrangement.SpaceEvenly else Arrangement.Start,
        modifier = modifier.horizontalScroll(rememberScrollState())
    ) {
        hourlyForecast.forEachIndexed { index, it ->
            val contentDescriptionText = stringResource(
                R.string.description_hourly_chance_of_rain_percentage_fmt,
                it.time,
                it.chanceOfRainPct.roundToInt()
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .apply {
                        if (hourlyForecast.size > 2) this.weight(1.0f)
                    }
                    .fillMaxHeight()
                    .padding(horizontal = 6.dp)
                    .semantics(mergeDescendants = true) { this.contentDescription = contentDescriptionText }
            ) {
                Box(
                    contentAlignment = Alignment.BottomCenter,
                    modifier = Modifier.weight(1.0f)
                ) {
                    Canvas(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(1.dp)
                    ) {
                        drawLine(
                            color = gray200,
                            Offset(0.0f, 0.0f),
                            Offset(0.0f, size.height - 1),
                            pathEffect = dashPathEffect(floatArrayOf(10.0f, 10.0f), 0.0f)
                        )
                    }
                    val animatedMaxHeightFraction by animateFloatAsState(
                        targetValue = 1.0f - (it.chanceOfRainPct / 100.0f).coerceAtMost(1.0f),
                        animationSpec = tween(durationMillis = 1000, delayMillis = 120)
                    )
                    Box(
                        modifier = Modifier
                            .width(20.dp)
                            .defaultMinSize(minHeight = 20.dp)
                            .fillMaxHeight(animatedMaxHeightFraction)
                            .clip(RoundedCornerShape(10.dp))
                            .background(if (isCurrentDate && index == 0) amber200 else gray200)
                    )
                }
                Spacer(modifier = Modifier.size(8.dp))
                Text(
                    maxLines = 1,
                    text = it.time,
                    style = MaterialTheme.typography.body2,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.clearAndSetSemantics { }
                )
            }
        }
    }
}

@Composable
private fun ForecastDashboardPreview(isLoading: Boolean = false) {
    var selectedLocation by remember { mutableStateOf(SAMPLE_AVAILABLE_LOCATIONS.first()) }
    var selectedDateIndex by remember { mutableStateOf(0) }

    val hasPreviousDayState = remember { derivedStateOf { (selectedDateIndex > 0) } }
    val hasNextDayState = remember { derivedStateOf { (selectedDateIndex < 4) } }

    CompositionLocalProvider(LocalRunMode provides RunMode.InPreview) {
        ForecastDashboard(
            isLoading = isLoading,
            availableLocations = SAMPLE_AVAILABLE_LOCATIONS,
            selectedLocation = selectedLocation,
            onSelectLocation = { selectedLocation = it },
            availableForecastDates = SAMPLE_AVAILABLE_FORECAST_DATES,
            selectedDateIndex = selectedDateIndex,
            selectedDailyForecast = makeSampleDailyForecast(selectedDateIndex),
            hasPreviousDayState = hasPreviousDayState,
            hasNextDayState = hasNextDayState,
            onSelectAnotherDay = { if (it) --selectedDateIndex else ++selectedDateIndex },
            onRefresh = {}
        )
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
private fun LightPreview() {
    MyTheme {
        ForecastDashboardPreview()
    }
}

@Preview("Light Theme (Loading)", widthDp = 360, heightDp = 640)
@Composable
private fun LoadingLightPreview() {
    MyTheme {
        ForecastDashboardPreview(isLoading = true)
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
private fun DarkPreview() {
    MyTheme(darkTheme = true) {
        ForecastDashboardPreview()
    }
}

@Preview(widthDp = 360, heightDp = 640, showBackground = true, backgroundColor = 0xFF5ACBFA)
@Composable
private fun ChanceOfRainPreview() {
    MyTheme {
        ChanceOfRainPanel(
            isCurrentDate = true,
            hourlyForecast = makeSampleDailyForecast(0).hourly
        )
    }
}

@Preview(widthDp = 360, heightDp = 640, showBackground = true, backgroundColor = 0xFF5ACBFA)
@Composable
private fun SingleHourChanceOfRainPreview() {
    MyTheme {
        ChanceOfRainPanel(
            isCurrentDate = true,
            hourlyForecast = makeSampleDailyForecast(0).hourly.take(1)
        )
    }
}

@Preview(widthDp = 360, heightDp = 640, showBackground = true, backgroundColor = 0xFF5ACBFA)
@Composable
private fun DoubleHourChanceOfRainPreview() {
    MyTheme {
        ChanceOfRainPanel(
            isCurrentDate = true,
            hourlyForecast = makeSampleDailyForecast(0).hourly.take(2)
        )
    }
}

@Preview(widthDp = 360, heightDp = 640, showBackground = true, backgroundColor = 0xFF5ACBFA)
@Composable
private fun TooManyHoursChanceOfRainPreview() {
    MyTheme {
        ChanceOfRainPanel(
            isCurrentDate = true,
            hourlyForecast = makeSampleDailyForecast(0).hourly + makeSampleDailyForecast(0).hourly
        )
    }
}
