package com.boringxcompany.charts.currency.data.domain

import kotlinx.serialization.Serializable

@Serializable
data class Data(
    val CoinInfo: CoinInfo,
    val DISPLAY: DISPLAY,
    val RAW: RAW
)