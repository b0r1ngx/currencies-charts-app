package com.boringxcompany.charts.currency.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.boringxcompany.charts.currency.repository.currency.CurrenciesRepository

class HomeViewModelFactory(
    private val currenciesRepository: CurrenciesRepository,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HomeViewModel(currenciesRepository) as T
        }
        throw IllegalArgumentException("Get unknown ViewModel at HomeViewModelFactory")
    }
}