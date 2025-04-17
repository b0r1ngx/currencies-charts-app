package com.boringxcompany.charts.currency.repository.currency

import com.boringxcompany.charts.currency.data.domain.GeneralCoinInfo

interface CurrenciesRepository {
    suspend fun getCurrencies(): List<GeneralCoinInfo>?
    suspend fun getCurrency(code: String): GeneralCoinInfo
}
