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
package com.wiyonoaten.composechallenge.wk4weatherforecastapp.ui.modifiers

import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.IntSize

fun Modifier.rememberLayoutSize(lambda: (IntSize) -> Unit) = layout { measurable, constraints ->
    val placeable = measurable.measure(constraints)
    lambda.invoke(IntSize(placeable.width, placeable.height))
    layout(placeable.width, placeable.height) {
        placeable.placeRelative(0, 0)
    }
}
