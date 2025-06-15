package repository.currency

import api.CoinDeskApi
import api.CurrencyApi
import client.KtorClient

fun determineBestCurrencyApiForUser(): CurrencyApi {
    return CoinDeskApi(KtorClient())
}
