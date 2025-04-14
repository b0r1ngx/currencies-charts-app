package com.boringxcompany.charts.currency.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.boringxcompany.charts.currency.data.domain.Currency
import com.boringxcompany.charts.currency.ui.chart.CurrencyChart
import com.boringxcompany.charts.currency.viewmodel.HomeViewModel

@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    val currencies = viewModel.currencies.collectAsState(listOf())
    Column {
        TopBar()
        LazyColumn {
            items(currencies.value) { currency ->
                CurrencyCard(currency)
            }
        }
    }
}

@Composable
private fun TopBar(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(text = "Name")
        Text(text = "Price")
        Text(text = "Chart")
    }
}

@Composable
private fun CurrencyCard(currency: Currency, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(text = currency.name)
        Text(text = currency.price.price.toString())
        CurrencyChart()
    }
}
