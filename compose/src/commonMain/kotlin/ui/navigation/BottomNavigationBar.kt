package ui.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import data.screen.Tabs

@Composable
fun BottomNavigationBar(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    // todo: check is this increase performance?
    val tabs = remember { Tabs.entries.toTypedArray() }
    NavigationBar {
        tabs.forEach { tab ->
            val isTabSelected = currentDestination?.route == tab.name

            NavigationBarItem(
                selected = isTabSelected,
                onClick = {
                    // Only navigate if we're not already on that tab
                    // Prevent building up a large back stack
                    if (currentDestination?.route != tab.name) {
                        navController.navigate(tab.name) {
                            launchSingleTop = true
                            restoreState = true
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                        }
                    }
                },
                icon = { Icon(imageVector = tab.icon, contentDescription = tab.title) },
                label = { Text(tab.title, color = notSelectedTextColor(isTabSelected)) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.surface,
                    indicatorColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    }
}

@Composable
private fun notSelectedTextColor(isSelected: Boolean) =
    if (isSelected) MaterialTheme.colorScheme.onSurface
    else Color.Gray // todo: use some color from MaterialTheme
