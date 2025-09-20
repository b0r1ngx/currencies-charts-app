package api

import com.boringxcompany.charts.currency.data.domain.GeneralCoinInfo
import com.boringxcompany.charts.currency.data.domain.Price

interface CurrencyApi {
    val base: String
    val accessKey: String
    val tag: String

    suspend fun getCurrencies(): List<GeneralCoinInfo>
    suspend fun getCurrency(code: String): List<Price>
}
