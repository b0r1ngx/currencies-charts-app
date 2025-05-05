package com.boringxcompany.charts.currency.api

import android.util.Log
import com.boringxcompany.charts.currency.client.Client
import com.boringxcompany.charts.currency.data.domain.GeneralCoinInfo
import com.boringxcompany.charts.currency.data.domain.coindesk.CoinDeskResponse
import com.boringxcompany.charts.currency.client.KtorClient
import com.boringxcompany.charts.currency.data.domain.Price
import com.boringxcompany.charts.currency.data.domain.coindesk.DailyWrapper
import com.boringxcompany.charts.currency.data.domain.coindesk.Data
import com.boringxcompany.charts.currency.data.domain.toLocalTime
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText

private const val IMAGE_BASE = "https://www.cryptocompare.com/"
private const val totalvolfullEndpoint = "/data/top/totalvolfull"
private const val currencyEndpoint = "/data/v2/histoday"

class CoinDeskApi<T>(private val client: Client<T>) : CurrencyApi {
    override val base = "https://min-api.cryptocompare.com"
    override val accessKey = "ae093614fb942ec40ec4d705e121edf55ad587feaa7b69757a4cf79ffcfaf418"
    override val tag = "COIN_DESK_TAG"

    override suspend fun getCurrencies(): List<GeneralCoinInfo> {
        try {
            when (client) {
                is KtorClient -> {
                    val httpClient = client.getClient()
                    val response = httpClient.get(
                        url = base + totalvolfullEndpoint,
                        parameters = arrayOf(
                            "limit" to "$AMOUNT_OF_CURRENCIES",
                            "tsym" to QUOTE_CURRENCY,
                            "api_key" to accessKey,
                        )
                    )

                    return response.body<CoinDeskResponse<List<Data>>>().mapToGeneralCoinInfo()
                }
            }
        } catch (e: Exception) {
            Log.e(tag, "CoinDeskApi.getCurrencies(). Exception: ${e.message}")
        }

        return listOf()
    }

    private suspend fun HttpClient.get(
        url: String,
        vararg parameters: Pair<String, String>,
    ): HttpResponse = get(url) {
        parameters.forEach { (name, value) ->
            parameter(name, value)
        }
    }

    private fun CoinDeskResponse<List<Data>>.mapToGeneralCoinInfo(): List<GeneralCoinInfo> {
        val coinList = ArrayList<GeneralCoinInfo>()

        data?.forEachIndexed { index, currency ->
            coinList.add(
                GeneralCoinInfo(
                    index = index + 1,
                    image = IMAGE_BASE + currency.coinInfo?.imageURL,
                    fullName = currency.coinInfo?.fullName,
                    code = currency.coinInfo?.name,
                    price = currency.display?.usd?.price,
                    dailyPriceChangePercent = currency.display?.usd?.dailyPriceChangePercent,
                    dailyTradeVolume = currency.display?.usd?.dailyTradeVolume,
                    marketCap = currency.display?.usd?.marketCap,
                )
            )
        }

        return coinList
    }

    override suspend fun getCurrency(code: String): List<Price> {
        try {
            when (client) {
                is KtorClient -> {
                    val httpClient = client.getClient()
                    val response = httpClient.get(
                        url = base + currencyEndpoint,
                        parameters = arrayOf(
                            "fsym" to code,
                            "tsym" to QUOTE_CURRENCY,
                            // limit = 10 days, default 30
                            "api_key" to accessKey,
                        )
                    )

                    println("code ${response.bodyAsText()}")

                    return response.body<CoinDeskResponse<DailyWrapper>>().mapToHistory()
                }
            }
        } catch (e: Exception) {
            Log.e(tag, "CoinDeskApi.getCurrency(). Exception: ${e.message}")
        }

        return listOf()
    }

    private fun CoinDeskResponse<DailyWrapper>.mapToHistory(): List<Price> {
        val coinList = ArrayList<Price>()

        data?.data?.forEach { day ->
            coinList.add(
                Price(
                    date = day.time.toLocalTime(),
                    high = day.high,
                    low = day.low,
                    open = day.open,
                    close = day.close
                )
            )
        }

        return coinList
    }

}
