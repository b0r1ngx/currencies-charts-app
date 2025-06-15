import repository.currency.CurrenciesLocalRepository
import repository.currency.CurrenciesRemoteRepository
import repository.currency.DefaultCurrenciesRepository
import ui.viewmodel.HomeViewModel

class Dependencies {
    val currenciesRepository = DefaultCurrenciesRepository(
        localRepository = CurrenciesLocalRepository(),
        remoteRepository = CurrenciesRemoteRepository()
    )

    val homeViewModel = HomeViewModel(currenciesRepository)

    init {
        println("Hello, Dependencies!")
    }
}
