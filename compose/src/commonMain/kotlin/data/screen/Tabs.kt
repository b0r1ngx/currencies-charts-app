package data.screen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

enum class Tabs(
    val title: String, // todo: change to @StringRes title: Int
    val icon: ImageVector,
) {
    HOME(title = "Home", icon = Icons.Default.Home),
    PROFILE(title = "Profile", icon = Icons.Default.Person),
}
