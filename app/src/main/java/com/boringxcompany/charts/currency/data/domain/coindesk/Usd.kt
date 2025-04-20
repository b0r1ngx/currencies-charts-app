package com.boringxcompany.charts.currency.data.domain.coindesk

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Usd(
    @SerialName("PRICE")
    val price: String?,
    @SerialName("CHANGEPCTDAY")
    val dailyPriceChangePercent: String?,
    @SerialName("TOTALVOLUME24HTO")
    val dailyTradeVolume: String?,
    @SerialName("MKTCAP")
    val marketCap: String?
)
