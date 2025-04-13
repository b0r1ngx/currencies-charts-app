package com.boringxcompany.charts.currency.repository.currency

import com.boringxcompany.charts.currency.data.domain.Currency

interface CurrenciesRepository {
    suspend fun getCurrencies(): List<Currency>?
    suspend fun getCurrency(code: String): Currency
}
