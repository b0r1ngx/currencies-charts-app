package com.boringxcompany.charts.currency.data.domain

import kotlinx.serialization.Serializable

@Serializable
data class Weiss(
    val MarketPerformanceRating: String,
    val Rating: String,
    val TechnologyAdoptionRating: String
)