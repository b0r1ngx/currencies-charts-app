package com.boringxcompany.charts.currency.data.domain.coindesk

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Display(
    @SerialName("USD")
    val usd: Usd,
)
