package com.boringxcompany.charts.currency.data.domain

data class Currency(
    val name: String,
    val price: Float,
    val history: List<Float>,
)
