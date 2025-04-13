package com.boringxcompany.charts.currency.ui.screen

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.boringxcompany.charts.currency.data.screen.Screens

@Composable
fun BottomNavigationBar(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar {
        Screens.entries.forEach { screen ->
            val isScreenSelected = currentDestination?.route == screen.name

            NavigationBarItem(
                selected = isScreenSelected,
                onClick = {
                    // Only navigate if we're not already on that screen
                    // Prevent building up a large back stack
                    if (currentDestination?.route != screen.name) {
                        navController.navigate(screen.name) {
                            launchSingleTop = true
                            restoreState = true
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                        }
                    }
                },
                icon = {
                    Icon(imageVector = screen.icon, contentDescription = screen.title)
                },
                label = {
                    Text(
                        text = screen.title,
                        color = notSelectedTextColor(isScreenSelected)
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.surface,
                    indicatorColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    }
}

@Composable
fun notSelectedTextColor(isSelected: Boolean) =
    if (isSelected) MaterialTheme.colorScheme.onSurface
    else Color.Gray // todo: use some color from MaterialTheme
