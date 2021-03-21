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
