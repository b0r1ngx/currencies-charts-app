package com.boringxcompany.charts.currency.repository.currency

import com.boringxcompany.charts.currency.data.domain.Currency

class CurrenciesRemoteRepository : CurrenciesRepository {
    override suspend fun getCurrencies(): List<Currency>? {
        TODO("Not yet implemented")
    }

    override suspend fun getCurrency(code: String): Currency {
        TODO("Not yet implemented")
    }

}
