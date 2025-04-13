package com.boringxcompany.charts.currency.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import com.boringxcompany.charts.currency.data.domain.Currency
import com.boringxcompany.charts.currency.viewmodel.HomeViewModel

@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    val currencies = viewModel.currencies.collectAsState(listOf())
    LazyColumn {
        // TopBar()
        items(currencies.value) { currency ->
            CurrencyCard(currency)
        }
    }
}

@Composable
private fun TopBar(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(text = "Name")
        Text(text = "Price")
        Text(text = "Graph")
    }
}

@Composable
private fun CurrencyCard(currency: Currency, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(text = currency.name)
        Text(text = currency.price.toString())
        Chart(data = currency.history)
    }
}

@Composable
fun Chart(data: List<Float>) {
    Column(
        modifier = Modifier.drawBehind {
//            drawPath()
        }
    ) {  }
}
