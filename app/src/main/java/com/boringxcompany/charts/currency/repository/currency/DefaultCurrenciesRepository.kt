package com.boringxcompany.charts.currency.repository.currency

import com.boringxcompany.charts.currency.data.domain.GeneralCoinInfo

class DefaultCurrenciesRepository(
    private val localRepository: CurrenciesLocalRepository,
    private val remoteRepository: CurrenciesRemoteRepository
) : CurrenciesRepository {

    override suspend fun getCurrencies(): List<GeneralCoinInfo> {
        val currencies = remoteRepository.getCurrencies()
        if (currencies != null) {
            localRepository.saveCurrencies(currencies)
        }
        return currencies!!
    }

    override suspend fun getCurrency(code: String): GeneralCoinInfo {
        TODO("Not yet implemented")
    }

}
