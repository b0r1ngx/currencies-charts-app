package com.boringxcompany.charts.currency.data.domain.coindesk

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CoinDeskResponse(
    @SerialName("Data")
    val data: List<Data>,
)



