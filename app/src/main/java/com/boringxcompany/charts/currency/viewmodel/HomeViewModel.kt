package com.boringxcompany.charts.currency.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.boringxcompany.charts.currency.data.domain.Currency
import com.boringxcompany.charts.currency.repository.currency.CurrenciesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val currenciesRepository: CurrenciesRepository,
) : ViewModel() {
    private val _currencies: MutableStateFlow<List<Currency>> = MutableStateFlow(listOf())
    val currencies: SharedFlow<List<Currency>> = _currencies.asStateFlow()

    init {
        collectCurrencies()
    }

    fun collectCurrencies() {
        viewModelScope.launch {
            try {
                val currencies = currenciesRepository.getCurrencies()
                if (currencies != null) {
                    _currencies.update { currencies }
                }
            } catch (e: Exception) {
                // todo
            }
        }
    }

}
