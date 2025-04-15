package com.boringxcompany.charts.currency.data.domain

data class CoinInfo(
    val Algorithm: String,
    val AssetLaunchDate: String,
    val BlockNumber: Int,
    val BlockReward: Double,
    val BlockTime: Double,
    val DocumentType: String,
    val FullName: String,
    val Id: String,
    val ImageUrl: String,
    val Internal: String,
    val MaxSupply: Double,
    val Name: String,
    val NetHashesPerSecond: Long,
    val ProofType: String,
    val Rating: Rating,
    val Type: Int,
    val Url: String
)