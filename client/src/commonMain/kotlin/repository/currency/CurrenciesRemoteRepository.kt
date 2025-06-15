package repository.currency

import api.CurrencyApi
import com.boringxcompany.charts.currency.data.domain.GeneralCoinInfo
import com.boringxcompany.charts.currency.data.domain.Price

class CurrenciesRemoteRepository(
    private val api: CurrencyApi = determineBestCurrencyApiForUser(),
) : CurrenciesRepository {

    override suspend fun getCurrencies(): List<GeneralCoinInfo> {
        return api.getCurrencies()
    }

    override suspend fun getCurrency(code: String): List<Price> {
        return api.getCurrency(code)
    }
}
