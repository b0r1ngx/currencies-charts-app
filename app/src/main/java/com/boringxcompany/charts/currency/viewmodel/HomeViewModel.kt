package com.boringxcompany.charts.currency.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.boringxcompany.charts.currency.data.domain.GeneralCoinInfo
import com.boringxcompany.charts.currency.repository.currency.CurrenciesRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

private const val tag = "HomeViewModel_TAG"


class HomeViewModel(
    private val currenciesRepository: CurrenciesRepository,
) : ViewModel() {
    private val _currencies = mutableStateListOf<GeneralCoinInfo>()
    val currencies: List<GeneralCoinInfo>
        get() = _currencies

    // If concerned about locking or using this in very high-frequency scrolls,
    // consider replacing mutableSetOf() with ConcurrentHashMap.newKeySet() to reduce contention.
    private val fetchedHistory = mutableSetOf<String>()
    private val fetchingHistory = mutableSetOf<String>()
    private val historyMutex = Mutex()

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
                Log.e(tag, "collectCurrencies(). Exception: ${e.message}")
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
                Log.e(tag, "collectCurrencyHistory(code=$code). Exception: ${e.message}")
            } finally {
                historyMutex.withLock { fetchingHistory.remove(code) }
            }
        }
    }

}
