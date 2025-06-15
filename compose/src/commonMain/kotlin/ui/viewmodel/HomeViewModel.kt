package ui.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.boringxcompany.charts.currency.data.domain.GeneralCoinInfo
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import repository.currency.CurrenciesRepository

private const val tag = "HomeViewModel_TAG"

class HomeViewModel(
    private val currenciesRepository: CurrenciesRepository,
//    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _currencies = mutableStateListOf<GeneralCoinInfo>()
    val currencies: List<GeneralCoinInfo>
        get() = _currencies

    // If concerned about locking or using this in very high-frequency scrolls,
    // consider replacing mutableSetOf() with ConcurrentHashMap.newKeySet() to reduce contention.
    private val fetchedHistory = mutableSetOf<String>()
    private val fetchingHistory = mutableSetOf<String>()
    private val historyMutex = Mutex()

    // TODO: if want self-implemented analytics of: frequency of specific screen appearing to user
    //       1. create MainViewModel: Viewmodel(),
    //       2. in MainViewModel:
    //          create unique screen_appear_key (by class name)
    //          create screenAppearanceCounter & onScreenAppear()
    //          (also we can hold structure with all keys in common place, to check that they all unique)
    //       3. then use in app SpecificScreenViewModel: MainViewModel()
    private val SCREEN_APPEAR_KEY = "HOME_SCREEN_APPEAR"

//    @OptIn(SavedStateHandleSaveableApi::class)
//    private val screenAppearanceCounter = savedStateHandle.saveable(SCREEN_APPEAR_KEY) {
//        mutableIntStateOf(0)
//    }
//
//    fun onScreenAppear() {
//        // use this
//        savedStateHandle[SCREEN_APPEAR_KEY] = savedStateHandle.get<Int>(SCREEN_APPEAR_KEY)?.plus(1)
//
//        // or this is also allowed and will be auto-increased at savedStateHandle?
//        screenAppearanceCounter.intValue += 1
//    }

    init {
        collectCurrencies()
    }

    private fun collectCurrencies() {
        viewModelScope.launch {
            try {
                if (_currencies.isNotEmpty()) return@launch
                val currencies = currenciesRepository.getCurrencies()
                _currencies.addAll(currencies)
            } catch (e: Exception) {
                print("$tag. collectCurrencies(). Exception: ${e.message}")
//                Log.e(tag, "collectCurrencies(). Exception: ${e.message}")
            }
        }
    }

    fun collectCurrencyHistory(code: String) {
        viewModelScope.launch {
            historyMutex.withLock {
                if (code in fetchedHistory || code in fetchingHistory) return@launch
                fetchingHistory.add(code)
            }

            try {
                val history = currenciesRepository.getCurrency(code)
                if (history.isEmpty()) return@launch

                val index = _currencies.indexOfFirst { it.code == code }
                if (index != -1) {
                    val updated = _currencies[index].copy(history = history)
                    _currencies[index] = updated
                }

                historyMutex.withLock { fetchedHistory.add(code) }
            } catch (e: Exception) {
                print("$tag. collectCurrencyHistory(code=$code). Exception: ${e.message}")
//                Log.e(tag, "collectCurrencyHistory(code=$code). Exception: ${e.message}")
            } finally {
                historyMutex.withLock { fetchingHistory.remove(code) }
            }
        }
    }

}
