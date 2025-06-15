package api

import com.boringxcompany.charts.currency.data.domain.GeneralCoinInfo
import com.boringxcompany.charts.currency.data.domain.Price

const val AMOUNT_OF_CURRENCIES = 100
// TODO: Allow to set this in user preferences (right on home screen, to be more visible / easy to change, or move to ProfileScreen?)
const val QUOTE_CURRENCY = "USD"

interface CurrencyApi {
    val base: String
    val accessKey: String
    val tag: String

    suspend fun getCurrencies(): List<GeneralCoinInfo>
    suspend fun getCurrency(code: String): List<Price>
}
