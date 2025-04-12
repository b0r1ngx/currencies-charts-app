package com.boringxcompany.charts.currency.repository.currency

import com.boringxcompany.charts.currency.data.Currency

class CurrencyLocalRepository : CurrencyRepository {
    override suspend fun getCurrency(code: String): List<Currency>? {
        TODO("Not yet implemented")
        // идем в локал датабейз, получаем данные
    }

    suspend fun saveCurrencies(currencies: List<Currency>) {
        currencies.forEach { currency ->
            saveCurrency(currency)
        }
    }

    suspend fun saveCurrency(currency: Currency) {

    }

}
