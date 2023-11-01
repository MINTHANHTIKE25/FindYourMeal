package com.example.findyourmeal.startup.scnmain

sealed class BottomNavPages(val route:String){
    object HomeScreen:BottomNavPages("home_screen")
    object SearchScreen:BottomNavPages("search_screen")
    object FavScreen:BottomNavPages("fav_screen")
    object SettingScreen:BottomNavPages("setting_screen")
}
