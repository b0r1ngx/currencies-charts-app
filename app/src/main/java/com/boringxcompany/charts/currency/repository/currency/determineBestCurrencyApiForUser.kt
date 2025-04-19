package com.boringxcompany.charts.currency.repository.currency

import com.boringxcompany.charts.currency.api.CoinDeskApi
import com.boringxcompany.charts.currency.api.CurrencyApi
import com.boringxcompany.charts.currency.client.KtorClient

fun determineBestCurrencyApiForUser(): CurrencyApi {
    return CoinDeskApi(KtorClient())
}
