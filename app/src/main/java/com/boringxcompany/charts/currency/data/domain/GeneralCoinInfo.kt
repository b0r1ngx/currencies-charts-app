package com.boringxcompany.charts.currency.data.domain

data class GeneralCoinInfo(
    val index: Int,
    val image: String?,
    val fullName: String?,
    val code: String?,
    val price: String?,
    val dailyPriceChangePercent: String?,
    val dailyTradeVolume: String?,
    val marketCap: String?,
    val history: List<Price> = emptyList(),
)
