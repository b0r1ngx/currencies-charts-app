package com.boringxcompany.charts.currency.repository.currency

import com.boringxcompany.charts.currency.data.Currency

interface CurrencyRepository {
    suspend fun getCurrency(code: String): List<Currency>?
}
