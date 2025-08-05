package com.boringxcompany.charts.currency.data.domain

import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

data class Price(
    val date: LocalTime,
    val high: Float,
    val low: Float,
    val open: Float,
    val close: Float
)

// unixtime to LocalTime
@OptIn(ExperimentalTime::class)
fun Long.toLocalTime(): LocalTime {
    return Instant.fromEpochSeconds(this)
        .toLocalDateTime(TimeZone.currentSystemDefault())
        .time
}