package com.boringxcompany.charts.currency.data.domain.coindesk

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Data(
    @SerialName("CoinInfo")
    val coinInfo: CoinInfo?,
    @SerialName("DISPLAY")
    val display: Display?
)
