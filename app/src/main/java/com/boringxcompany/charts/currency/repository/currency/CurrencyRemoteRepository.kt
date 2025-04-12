package com.boringxcompany.charts.currency.repository.currency

import com.boringxcompany.charts.currency.data.Currency

class CurrencyRemoteRepository : CurrencyRepository {
    override suspend fun getCurrency(code: String): List<Currency>? {
        TODO("Not yet implemented")
    }

}
