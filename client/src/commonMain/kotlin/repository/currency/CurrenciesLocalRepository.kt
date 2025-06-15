package repository.currency

import com.boringxcompany.charts.currency.data.domain.GeneralCoinInfo
import com.boringxcompany.charts.currency.data.domain.Price

class CurrenciesLocalRepository(
    // localDataSource
) : CurrenciesRepository {
    override suspend fun getCurrencies(): List<GeneralCoinInfo> {
        TODO("Not yet implemented")
        // идем в локал датабейз, получаем данные
    }

    override suspend fun getCurrency(code: String): List<Price> {
        TODO("Not yet implemented")
    }

    suspend fun saveCurrencies(currencies: List<GeneralCoinInfo>) {
        currencies.forEach { currency ->
            saveCurrency(currency)
        }
    }

    suspend fun saveCurrency(generalCoinInfo: GeneralCoinInfo) {

    }

}
