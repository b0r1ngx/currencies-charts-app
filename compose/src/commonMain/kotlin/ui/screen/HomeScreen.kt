package ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.boringxcompany.charts.currency.data.domain.GeneralCoinInfo
import ui.chart.CurrencyChart
import ui.viewmodel.HomeViewModel

private enum class ColumnWeight(val weight: Float) {
    Index(0.5f), Name(2f), Price(1f), DailyChange(1f),
    Volume(1.5f), MarketCap(1.5f), History(3f)
}

// TODO: Allow user to choose type of chart
@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    modifier: Modifier = Modifier,
) {
    val currenciesLazyListState = rememberLazyListState()
    LaunchedEffect(Unit) {
        snapshotFlow { currenciesLazyListState.layoutInfo.visibleItemsInfo }
            .collect { visibleCurrencies ->
                visibleCurrencies.forEach { item ->
                    viewModel.currencies.getOrNull(item.index)?.code?.let { code ->
                        viewModel.collectCurrencyHistory(code)
                    }
                }
            }
    }

    Column(modifier = modifier) {
        TitleRow()
        LazyColumn(state = currenciesLazyListState) {
            // todo: fix !!
            items(
                count = viewModel.currencies.size,
                key = { index -> viewModel.currencies[index].code!! }
            ) { index ->
                val currency = viewModel.currencies[index]
                CurrencyRow(currency)
            }
        }
    }
}

// todo: on default phone screen, remove volume(24h)
// todo: allow user to choose what columns he wants to see (test variants that user possible to choose)
@Composable
private fun TitleRow(modifier: Modifier = Modifier) {
    CurrencyRow(modifier = modifier) {
        Cell("#", ColumnWeight.Index)
        Cell("Name", ColumnWeight.Name)
        Cell("Price", ColumnWeight.Price)
        Cell("24h%", ColumnWeight.DailyChange)
        Cell("Volume(24h)", ColumnWeight.Volume)
        Cell("Market Cap", ColumnWeight.MarketCap)
        Cell("History (30 days)", ColumnWeight.History)
    }
}

@Composable
private fun CurrencyRow(currency: GeneralCoinInfo, modifier: Modifier = Modifier) {
    CurrencyRow(modifier = modifier) {
        Cell("#${currency.index}", ColumnWeight.Index)
        NameCell(currency, ColumnWeight.Name)
        Cell(currency.price.toString(), ColumnWeight.Price)
        Cell(currency.dailyPriceChangePercent.toString(), ColumnWeight.DailyChange)
        Cell(currency.dailyTradeVolume.toString(), ColumnWeight.Volume)
        Cell(currency.marketCap.toString(), ColumnWeight.MarketCap)
        CurrencyChart(
            data = currency.history,
            modifier = Modifier.weight(ColumnWeight.History.weight)
        )
    }
}

@Composable
private fun CurrencyRow(modifier: Modifier = Modifier, content: @Composable RowScope.() -> Unit) =
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp), // todo: setting up it later
        verticalAlignment = Alignment.CenterVertically,
        content = content
    )

@Composable
private fun RowScope.Cell(
    text: String,
    weight: ColumnWeight,
    modifier: Modifier = Modifier,
    style: TextStyle = MaterialTheme.typography.bodySmall,
) = Text(
    text = text,
    modifier = modifier.weight(weight.weight),
    textAlign = TextAlign.Center,
    style = style,
)

@Composable
private fun RowScope.NameCell(
    currency: GeneralCoinInfo,
    weight: ColumnWeight,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.weight(weight.weight),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AsyncImage(
            model = currency.image,
            contentDescription = null,
            modifier = Modifier.size(32.dp).padding(end = 4.dp),
        )
        Column {
            Text(text = currency.fullName.toString(), style = MaterialTheme.typography.bodySmall)
            Text(text = currency.code.toString(), style = MaterialTheme.typography.labelSmall)
        }
    }
}
