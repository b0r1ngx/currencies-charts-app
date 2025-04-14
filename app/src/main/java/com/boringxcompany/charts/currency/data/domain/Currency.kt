package com.boringxcompany.charts.currency.data.domain

data class Currency(
    val name: String,
    val price: Price,
    val history: List<Price>,
)
