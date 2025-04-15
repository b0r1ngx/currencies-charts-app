package com.boringxcompany.charts.currency.data.domain

data class Response(
    val Data: List<Data>,
    val HasWarning: Boolean,
    val Message: String,
    val MetaData: MetaData,
    val RateLimit: RateLimit,
    val SponsoredData: List<Any?>,
    val Type: Int
)