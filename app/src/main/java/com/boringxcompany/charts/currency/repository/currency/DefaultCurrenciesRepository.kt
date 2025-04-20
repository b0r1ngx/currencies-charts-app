package com.boringxcompany.charts.currency.repository.currency

import com.boringxcompany.charts.currency.data.domain.GeneralCoinInfo
import com.boringxcompany.charts.currency.data.domain.Price

class DefaultCurrenciesRepository(
    private val localRepository: CurrenciesLocalRepository,
    private val remoteRepository: CurrenciesRemoteRepository
) : CurrenciesRepository {

    override suspend fun getCurrencies(): List<GeneralCoinInfo> {
        val currencies = remoteRepository.getCurrencies()

        if (currencies.isEmpty()) {
            return localRepository.getCurrencies()
        } else {
            localRepository.saveCurrencies(currencies)
        }

        return currencies
    }

    override suspend fun getCurrency(code: String): List<Price> {
        TODO("Not yet implemented")
    }

}
