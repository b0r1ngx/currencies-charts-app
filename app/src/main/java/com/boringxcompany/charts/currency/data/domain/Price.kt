package com.boringxcompany.charts.currency.data.domain

import java.time.Instant
import java.time.LocalTime
import java.time.ZoneId

data class Price(
    val date: LocalTime,
    val high: Float,
    val low: Float,
    val open: Float,
    val close: Float
)

// unixtime to LocalTime
fun Long.toLocalTime(): LocalTime {
    return Instant.ofEpochSecond(this)
        .atZone(ZoneId.systemDefault())
        .toLocalTime()
}
