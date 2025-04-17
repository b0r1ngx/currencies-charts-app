package com.boringxcompany.charts.currency.repository.currency

import android.util.Log
import com.boringxcompany.charts.currency.data.domain.GeneralCoinInfo
import com.boringxcompany.charts.currency.data.domain.coindesk.CoinDeskResponse
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.takeFrom

class CurrenciesRemoteRepository(private val myClient: MyHttpClient) : CurrenciesRepository {
    private val url = "https://min-api.cryptocompare.com/data/top/totalvolfull? "
    private val imageUrl = "https://www.cryptocompare.com/"
    override suspend fun getCurrencies(): List<GeneralCoinInfo>? {
        val coinList = ArrayList<GeneralCoinInfo>()
        try {
            val httpRequest = myClient.getClient().get {
                this.url.takeFrom(this@CurrenciesRemoteRepository.url)
                parameter("limit", 100)
                parameter("tsym", "USD")
                parameter(
                    "api_key",
                    "ae093614fb942ec40ec4d705e121edf55ad587feaa7b69757a4cf79ffcfaf418"
                )
            }.body<CoinDeskResponse>()
            Log.d("MyLog", "getCurrencies: $httpRequest")
            httpRequest.data?.let { dataList ->
                for (i in 0 until dataList.size) {
                    val item = dataList[i]
                    coinList.add(
                        GeneralCoinInfo(
                            image = imageUrl + item.coinInfo?.imageURL,
                            fullName = item.coinInfo?.fullName,
                            name = item.coinInfo?.name,
                            price = item.display?.usd?.price,
                            dailyPriceChangePercent = item.display?.usd?.dailyPriceChangePercent,
                            dailyVolume = item.display?.usd?.dailyTradeVolume,
                            marketCap = item.display?.usd?.marketCap
                        )
                    )
                }
                return coinList
            }
        } catch (e: Exception) {
            Log.e("getElements", "Error: ${e.message}")
        }
        return coinList
    }

    override suspend fun getCurrency(code: String): GeneralCoinInfo {
        TODO("Not yet implemented")
    }

}
