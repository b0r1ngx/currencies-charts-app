package com.boringxcompany.charts.currency.repository.currency

import android.util.Log
import com.boringxcompany.charts.currency.data.domain.Currency
import com.boringxcompany.charts.currency.data.domain.Response
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.statement.request
import io.ktor.http.takeFrom

class CurrenciesRemoteRepository(private val myClient: MyHttpClient) : CurrenciesRepository {
    private val url = "https://min-api.cryptocompare.com/data/top/totalvolfull?"
    override suspend fun getCurrencies(): List<Currency>? {

        try {
            val httpRequest = myClient.getClient().get {
                this.url.takeFrom(this@CurrenciesRemoteRepository.url)
                parameter("tsym", "USD")
                parameter("api_key", "ae093614fb942ec40ec4d705e121edf55ad587feaa7b69757a4cf79ffcfaf418")
            }.body<Response>()
            Log.d("MyLog", "getCurrencies: ${httpRequest}")

        } catch (e: Exception) {
            Log.e("getElements", "Error: ${e.message}")
        }
        return listOf()
    }

    override suspend fun getCurrency(code: String): Currency {
        TODO("Not yet implemented")
    }

}
