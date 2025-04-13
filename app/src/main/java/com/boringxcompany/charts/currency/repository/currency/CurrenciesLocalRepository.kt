package com.boringxcompany.charts.currency.repository.currency

import com.boringxcompany.charts.currency.data.domain.Currency

class CurrenciesLocalRepository(
    // localDataSource
) : CurrenciesRepository {
    override suspend fun getCurrencies(): List<Currency>? {

        TODO("Not yet implemented")
        // идем в локал датабейз, получаем данные
    }

    override suspend fun getCurrency(code: String): Currency {
        TODO("Not yet implemented")
    }

    suspend fun saveCurrencies(currencies: List<Currency>) {
        currencies.forEach { currency ->
            saveCurrency(currency)
        }
    }

    suspend fun saveCurrency(currency: Currency) {

    }

}
