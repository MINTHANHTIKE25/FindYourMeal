package com.example.findyourmeal.startup.mainlayout.mainscreen

sealed class BottomNaviRoutes(val routes: String) {
    object HomeScreen : BottomNaviRoutes("home_screen")
    object SearchScreen : BottomNaviRoutes("search_screen")
    object FavScreen : BottomNaviRoutes("fav_screen")
    object SettingScreen : BottomNaviRoutes("setting_screen")
}
