package com.boringxcompany.charts.currency.api

import com.boringxcompany.charts.currency.data.domain.GeneralCoinInfo
import com.boringxcompany.charts.currency.data.domain.Price

const val AMOUNT_OF_CURRENCIES = 100
const val QUOTE_CURRENCY = "USD"

interface CurrencyApi {
    val base: String
    val accessKey: String
    val tag: String

    suspend fun getCurrencies(): List<GeneralCoinInfo>
    suspend fun getCurrency(code: String): List<Price>
}
