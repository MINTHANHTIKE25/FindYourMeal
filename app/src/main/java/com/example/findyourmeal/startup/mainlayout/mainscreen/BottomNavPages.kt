package com.example.findyourmeal.startup.mainlayout.mainscreen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector


//Giving model for bottom navigation in main screen
data class BottomNavPages(
    val route: String, val label: String, val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)

var navItems: List<BottomNavPages> = listOf(
    BottomNavPages(
        BottomNaviRoutes.HomeScreen.routes,
        "Home",
        Icons.Filled.Home,
        Icons.Outlined.Home
    ),

    BottomNavPages(
        BottomNaviRoutes.SearchScreen.routes,
        "Search",
        Icons.Filled.Search,
        Icons.Outlined.Search
    ),

    BottomNavPages(
        BottomNaviRoutes.FavScreen.routes,
        "Favourite",
        Icons.Filled.Favorite,
        Icons.Outlined.FavoriteBorder
    ),

    BottomNavPages(
        BottomNaviRoutes.SettingScreen.routes,
        "Settings", Icons.Filled.Settings, Icons.Outlined.Settings
    )
)
