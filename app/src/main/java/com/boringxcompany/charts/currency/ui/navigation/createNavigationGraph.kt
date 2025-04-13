package com.boringxcompany.charts.currency.ui.navigation

import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.createGraph
import com.boringxcompany.charts.currency.data.screen.Screens
import com.boringxcompany.charts.currency.ui.screen.HomeScreen
import com.boringxcompany.charts.currency.ui.screen.ProfileScreen
import com.boringxcompany.charts.currency.viewmodel.HomeViewModel

fun createNavigationGraph(
    navController: NavHostController,
    homeViewModel: HomeViewModel,
): NavGraph {
    return navController.createGraph(startDestination = Screens.HOME.name) {
        composable(route = Screens.HOME.name) {
            HomeScreen(homeViewModel)
        }

        composable(route = Screens.PROFILE.name) {
            ProfileScreen()
        }

    }
}
