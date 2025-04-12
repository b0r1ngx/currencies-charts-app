package com.boringxcompany.charts.currency.data

sealed class Screen(val rout: String) {
    object HomeScreen: Screen("home_screen")
    object FavoriteScreen: Screen("favorite_screen")
    object UserScreen: Screen("user_screen")
}