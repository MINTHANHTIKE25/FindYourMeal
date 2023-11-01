package com.example.findyourmeal.startup.scnmain


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.findyourmeal.viewmodel.MainViewModelForApi

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(viewModelForApi: MainViewModelForApi) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavBar(navController = navController) }
    ) { paddingvalues ->
        paddingvalues
        MainScnNavGraph(viewModelForApi = viewModelForApi, navController)
    }
}

//for home screen contents


//for each category design


@Composable
fun BottomNavBar(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val routes = listOf(
        BottomNavPages.HomeScreen.route,
        BottomNavPages.SearchScreen.route,
        BottomNavPages.FavScreen.route,
        BottomNavPages.SettingScreen.route
    )

    val selected = currentDestination?.hierarchy?.any { it.route == routes[0] } == true
    NavigationBar {
        NavigationBarItem(
            selected = selected,
            onClick = { navController.navigate(BottomNavPages.HomeScreen.route) },
            icon = {
                Icon(
                    imageVector = if (selected) {
                        Icons.Filled.Home
                    } else {
                        Icons.Outlined.Home
                    }, contentDescription = null
                )
            },
            label = { Text(text = "Home") },
        )
        NavigationBarItem(
            selected = selected,
            onClick = { navController.navigate(BottomNavPages.SearchScreen.route) },
            icon = {
                Icon(
                    imageVector = if (selected) {
                        Icons.Filled.Search
                    } else {
                        Icons.Outlined.Search
                    }, contentDescription = null
                )
            },
            label = { Text(text = "Search") }
        )
        NavigationBarItem(
            selected = selected,
            onClick = { navController.navigate(BottomNavPages.FavScreen.route) },
            icon = {
                Icon(
                    imageVector = if (selected) {
                        Icons.Filled.Favorite
                    } else {
                        Icons.Outlined.FavoriteBorder
                    }, contentDescription = null
                )
            },
            label = { Text(text = "Favorite") }
        )
        NavigationBarItem(
            selected = selected,
            onClick = { navController.navigate(BottomNavPages.SettingScreen.route) },
            icon = {
                Icon(
                    imageVector = if (selected) {
                        Icons.Filled.Settings
                    } else {
                        Icons.Outlined.Settings
                    }, contentDescription = null
                )
            },
            label = { Text(text = "Settings") }
        )
    }
}