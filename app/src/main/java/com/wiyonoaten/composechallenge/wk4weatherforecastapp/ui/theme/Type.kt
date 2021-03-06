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
package com.wiyonoaten.composechallenge.wk4weatherforecastapp.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.wiyonoaten.composechallenge.wk4weatherforecastapp.R

// Set of Material typography styles to start with
val typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily(Font(R.font.comfortaa_regular)),
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),
    body2 = TextStyle(
        fontFamily = FontFamily(Font(R.font.comfortaa_regular)),
        fontWeight = FontWeight.Normal,
        fontSize = 11.sp
    ),
    subtitle1 = TextStyle(
        fontFamily = FontFamily(Font(R.font.varela_round_regular)),
        fontWeight = FontWeight.Bold,
        fontSize = 10.sp
    ),
    h1 = TextStyle(
        fontFamily = FontFamily(Font(R.font.varela_round_regular)),
        fontWeight = FontWeight.Normal,
        fontSize = 72.sp
    ),
    h3 = TextStyle(
        fontFamily = FontFamily(Font(R.font.varela_round_regular)),
        fontWeight = FontWeight.Normal,
        fontSize = 28.sp
    ),
    h4 = TextStyle(
        fontFamily = FontFamily(Font(R.font.comfortaa_regular)),
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp
    ),
    h5 = TextStyle(
        fontFamily = FontFamily(Font(R.font.comfortaa_regular)),
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    h6 = TextStyle(
        fontFamily = FontFamily(Font(R.font.comfortaa_regular)),
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily(Font(R.font.comfortaa_regular)),
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp
    ),
)
