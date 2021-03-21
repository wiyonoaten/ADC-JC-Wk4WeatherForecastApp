package com.wiyonoaten.composechallenge.wk4weatherforecastapp.ui.utils

import com.wiyonoaten.composechallenge.wk4weatherforecastapp.viewmodels.types.LocationInfo

fun LocationInfo.toDisplayText() =
    "${placeName}${if (countryCode.isEmpty()) "" else ", $countryCode"}"
