package com.boringxcompany.charts.currency.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.boringxcompany.charts.currency.data.domain.GeneralCoinInfo
import com.boringxcompany.charts.currency.data.domain.Price
import com.boringxcompany.charts.currency.repository.currency.CurrenciesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val currenciesRepository: CurrenciesRepository,
) : ViewModel() {
    private val _currencies: MutableStateFlow<List<GeneralCoinInfo>> = MutableStateFlow(listOf())
    val currencies: SharedFlow<List<GeneralCoinInfo>> = _currencies.asStateFlow()

    private val _histories: MutableStateFlow<List<List<Price>>> = MutableStateFlow(listOf())
    val histories: SharedFlow<List<List<Price>>> = _histories.asStateFlow()

    init {
        collectCurrencies()
    }

    fun collectCurrencies() {
        viewModelScope.launch {
            try {
                val currencies = currenciesRepository.getCurrencies()
                _currencies.update { currencies }
            } catch (e: Exception) {
                // todo
            }
        }
    }

    fun collectCurrency(code: String): List<Price> {
        var history: List<Price> = listOf()
        viewModelScope.launch {
            // todo: maybe its more correct to update GeneralCoinInfo.history at _currencies
            try {
                history = currenciesRepository.getCurrency(code)
                _histories.update { listOf(history) }
            } catch (e: Exception) {
                // todo
            }
        }
        return history
    }

}
