package com.wiyonoaten.composechallenge.wk4weatherforecastapp.utils

import org.threeten.bp.LocalDateTime
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.ZoneOffset

fun Long.epochToOffsetDateTime(): OffsetDateTime =
    OffsetDateTime.of(LocalDateTime.ofEpochSecond(this, 0, ZoneOffset.UTC), ZoneOffset.UTC)
