import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import ui.navigation.BottomNavigationBar
import ui.navigation.createNavigationGraph
import ui.theme.CurrenciesChartsTheme

@Composable
fun ComposeEntryPoint(dependencies: Dependencies) {
    CurrenciesChartsTheme {
        val navController = rememberNavController()

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = { BottomNavigationBar(navController) }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                graph = createNavigationGraph(navController, dependencies),
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}
