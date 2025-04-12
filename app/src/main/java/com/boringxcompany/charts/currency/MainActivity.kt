package com.boringxcompany.charts.currency

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.createGraph
import com.boringxcompany.charts.currency.data.screen.Screens
import com.boringxcompany.charts.currency.ui.screen.BottomNavigationBar
import com.boringxcompany.charts.currency.ui.screen.HomeScreen
import com.boringxcompany.charts.currency.ui.screen.ProfileScreen
import com.boringxcompany.charts.currency.ui.theme.CurrenciesChartsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CurrenciesChartsTheme {
                val navController = rememberNavController()

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = { BottomNavigationBar(navController) }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        graph = createNavigationGraph(navController),
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

fun createNavigationGraph(navController: NavHostController): NavGraph {
    return navController.createGraph(startDestination = Screens.HOME.name) {
        composable(route = Screens.HOME.name) {
            HomeScreen()
        }

        composable(route = Screens.PROFILE.name) {
            ProfileScreen()
        }

    }
}
