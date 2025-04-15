package com.boringxcompany.charts.currency.data.domain

import kotlinx.serialization.Serializable

@Serializable
data class HomeUiState(
    val currencies: List<Currency>
)
