package com.boringxcompany.charts.currency

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.boringxcompany.charts.currency.repository.currency.CurrenciesLocalRepository
import com.boringxcompany.charts.currency.repository.currency.CurrenciesRemoteRepository
import com.boringxcompany.charts.currency.repository.currency.DefaultCurrenciesRepository
import com.boringxcompany.charts.currency.repository.currency.MyHttpClient
import com.boringxcompany.charts.currency.ui.navigation.createNavigationGraph
import com.boringxcompany.charts.currency.ui.screen.BottomNavigationBar
import com.boringxcompany.charts.currency.ui.theme.CurrenciesChartsTheme
import com.boringxcompany.charts.currency.viewmodel.HomeViewModel
import com.boringxcompany.charts.currency.viewmodel.HomeViewModelFactory
import io.ktor.client.HttpClient

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        val currenciesRepository = DefaultCurrenciesRepository(
            CurrenciesLocalRepository(),
            CurrenciesRemoteRepository(MyHttpClient())
        )

        val homeViewModel: HomeViewModel by viewModels { HomeViewModelFactory(currenciesRepository) }

        setContent {
            CurrenciesChartsTheme {
                val navController = rememberNavController()

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = { BottomNavigationBar(navController) }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        graph = createNavigationGraph(navController, homeViewModel),
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
