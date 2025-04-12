package com.boringxcompany.charts.currency.data

sealed class Screen(val route: String) {
    object HomeScreen: Screen("home_screen")
    object FavoriteScreen: Screen("favorite_screen")
    object UserScreen: Screen("user_screen")
}