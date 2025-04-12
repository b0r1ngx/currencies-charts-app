package com.boringxcompany.charts.currency.ui.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.createGraph
import com.boringxcompany.charts.currency.data.Screen

@Composable
fun MainScreen() {

    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        bottomBar = { BottomNavigationBar(navController) }
    ) { innerPadding ->

        val graph =
            navController.createGraph(startDestination = Screen.HomeScreen.route) {
                composable(route = Screen.HomeScreen.route) {
                    HomeScreen()
                }
                composable(route = Screen.FavoriteScreen.route) {
                    FavoriteScreen()
                }
                composable(route = Screen.UserScreen.route) {
                    UserScreen()
                }
            }
        NavHost(
            navController = navController,
            graph = graph,
            modifier = Modifier.padding(innerPadding)
        )

    }
}