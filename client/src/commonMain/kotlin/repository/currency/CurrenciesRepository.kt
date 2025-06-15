package repository.currency

import com.boringxcompany.charts.currency.data.domain.GeneralCoinInfo
import com.boringxcompany.charts.currency.data.domain.Price

interface CurrenciesRepository {
    suspend fun getCurrencies(): List<GeneralCoinInfo>
    suspend fun getCurrency(code: String): List<Price>
}
