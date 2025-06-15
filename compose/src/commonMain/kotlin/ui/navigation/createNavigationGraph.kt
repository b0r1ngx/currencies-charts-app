package ui.navigation

import Dependencies
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.createGraph
import data.screen.Tabs
import ui.screen.HomeScreen
import ui.screen.ProfileScreen

fun createNavigationGraph(
    navController: NavHostController,
    dependencies: Dependencies,
): NavGraph {
    return navController.createGraph(startDestination = Tabs.HOME.name) {
        composable(route = Tabs.HOME.name) {
            HomeScreen(dependencies.homeViewModel)
        }

        composable(route = Tabs.PROFILE.name) {
            ProfileScreen()
        }

    }
}
