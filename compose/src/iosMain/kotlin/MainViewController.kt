import androidx.compose.ui.window.ComposeUIViewController
import platform.UIKit.UIViewController

@Suppress("unused", "FunctionName")
fun MainViewController(dependencies: Dependencies): UIViewController = ComposeUIViewController {
    ComposeEntryPoint(dependencies)
}