package com.boringxcompany.charts.currency.data.domain.coindesk

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DailyWrapper(
    @SerialName("Data")
    val data: List<Daily>,
)

@Serializable
data class Daily(
    val time: Long,
    val high: Float,
    val low: Float,
    val open: Float,
    val close: Float
)
