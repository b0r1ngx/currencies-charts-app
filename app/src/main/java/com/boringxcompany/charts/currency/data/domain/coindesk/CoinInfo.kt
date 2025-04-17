package com.boringxcompany.charts.currency.data.domain.coindesk

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CoinInfo(
    @SerialName("Name")
    val name: String,
    @SerialName("FullName")
    val fullName: String,
    @SerialName("ImageUrl")
    val imageURL: String,
)