package com.boringxcompany.charts.currency.data.domain.coindesk

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CoinDeskResponse<T>(
    @SerialName("Data")
    val data: T?,
)
