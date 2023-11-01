package com.example.findyourmeal.startup.scnmain


import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomNavigation
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import coil.compose.AsyncImage
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.findyourmeal.R
import com.example.findyourmeal.model.allcategories.Category
import com.example.findyourmeal.viewmodel.MainViewModelForApi

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Home(navController: NavController, viewModelForApi: MainViewModelForApi) {
    Scaffold(
        bottomBar ={ BottomNavBar(navController = navController)}
    ) {
        HomeScreen(navController = navController, viewModelForApi = viewModelForApi)
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
        BottomNavPages.SettingScreen
    )
    BottomNavigation (){
        NavigationBarItem(
            selected = currentDestination?.hierarchy?.any { it.route == routes[0] } == true,
            onClick = { navController.navigate(BottomNavPages.HomeScreen.route) },
            icon = { Icon(imageVector = Icons.Filled.Home, contentDescription = null)},
            label = { Text(text = "Home") },
        )
        NavigationBarItem(
            selected = currentDestination?.hierarchy?.any { it.route == routes[0] } == true,
            onClick = { navController.navigate(BottomNavPages.SearchScreen.route) },
            icon = { Icon(imageVector = Icons.Filled.Search, contentDescription = null) },
            label = { Text(text = "Search") }
        )
        NavigationBarItem(
            selected = currentDestination?.hierarchy?.any { it.route == routes[0] } == true,
            onClick = { navController.navigate(BottomNavPages.FavScreen.route) },
            icon = { Icon(imageVector = Icons.Filled.Favorite, contentDescription = null) },
            label = { Text(text = "Favorite") }
        )
        NavigationBarItem(
            selected = currentDestination?.hierarchy?.any { it.route == routes[0] } == true,
            onClick = { navController.navigate(BottomNavPages.SettingScreen.route) },
            icon = { Icon(imageVector = Icons.Filled.Settings, contentDescription = null) },
            label = { Text(text = "Settings") }
        )
    }
}