package com.boringxcompany.charts.currency

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import androidx.navigation.createGraph
import com.boringxcompany.charts.currency.data.Screen
import com.boringxcompany.charts.currency.ui.screen.BottomNavigationBar
import com.boringxcompany.charts.currency.ui.screen.MainScreen
import com.boringxcompany.charts.currency.ui.theme.CurrenciesChartsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CurrenciesChartsTheme {
                    MainScreen()
            }
        }
    }
}
