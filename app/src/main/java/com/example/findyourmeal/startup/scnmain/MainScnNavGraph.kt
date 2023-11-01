package com.example.findyourmeal.startup.scnmain

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.findyourmeal.viewmodel.MainViewModelForApi

@Composable
fun MainScnNavGraph(viewModelForApi: MainViewModelForApi,navController:NavHostController) {

    NavHost(navController = navController, startDestination = BottomNavPages.HomeScreen.route){
        composable(BottomNavPages.HomeScreen.route){
            HomeScreen(navController,viewModelForApi)
        }
        composable(BottomNavPages.SearchScreen.route){
            SearchScn(navController)
        }
        composable(BottomNavPages.FavScreen.route){
            FavScn(navController)
        }
        composable(BottomNavPages.SettingScreen.route){
            SettingScn(navController)
        }
    }
}