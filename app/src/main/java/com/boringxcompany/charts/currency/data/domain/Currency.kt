package com.boringxcompany.charts.currency.data.domain

import kotlinx.serialization.Serializable
@Serializable
data class Currency(
    val sortName: String?,
    val fullName: String?,
    val image: String?,
    val price: Double?,
    val dailyPriceChangePercent: Double?,
    val dailyVolume: Double?,
    val marketCap: Double?,
)
