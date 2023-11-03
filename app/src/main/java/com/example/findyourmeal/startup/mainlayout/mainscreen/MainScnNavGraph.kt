package com.example.findyourmeal.startup.mainlayout.mainscreen

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.findyourmeal.room.SavedDataViewModelFactory
import com.example.findyourmeal.startup.mainlayout.FavScn
import com.example.findyourmeal.startup.mainlayout.HomeScreen
import com.example.findyourmeal.startup.mainlayout.SettingScn
import com.example.findyourmeal.startup.mainlayout.search.SearchScn
import com.example.findyourmeal.viewmodel.MainViewModelForApi

//Setting navigation host for main screen
@Composable
fun MainScnNavGraph(viewModelForApi: MainViewModelForApi, navController: NavHostController,
                    factory: SavedDataViewModelFactory,
                    mainNavController: NavController) {

    NavHost(navController = navController, startDestination = BottomNaviRoutes.HomeScreen.routes) {
        composable(BottomNaviRoutes.HomeScreen.routes) {
            HomeScreen(navController, viewModelForApi,factory)
        }
        composable(BottomNaviRoutes.SearchScreen.routes) {
            SearchScn(mainNavController, viewModelForApi)
        }
        composable(BottomNaviRoutes.FavScreen.routes) {
            FavScn(navController,factory)
        }
        composable(BottomNaviRoutes.SettingScreen.routes) {
            SettingScn()
        }
    }
}