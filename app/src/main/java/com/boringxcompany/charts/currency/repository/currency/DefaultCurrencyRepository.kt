package com.boringxcompany.charts.currency.repository.currency

import com.boringxcompany.charts.currency.data.domain.Currency

class DefaultCurrencyRepository(
    private val localRepository: CurrencyLocalRepository,
    private val remoteRepository: CurrencyLocalRepository
) : CurrencyRepository {

    override suspend fun getCurrency(code: String): List<Currency> {
        val currencies = remoteRepository.getCurrency(code)
        if (currencies != null) {
            localRepository.saveCurrencies(currencies)
        }
        return currencies!!
    }

}
