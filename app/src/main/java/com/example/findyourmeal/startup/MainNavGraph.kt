package com.example.findyourmeal.startup

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.findyourmeal.startup.scnmain.Home
import com.example.findyourmeal.startup.scnmain.MainScnNavGraph
import com.example.findyourmeal.viewmodel.MainViewModelForApi

@Composable
fun MainNavSetup(navController: NavHostController,viewModelForApi: MainViewModelForApi) {
    NavHost(navController = navController, startDestination = StartUpScreen.SplashScreen.route){
        composable(route = StartUpScreen.SplashScreen.route){
            SplashScn(navController = navController)
        }
        composable(route = StartUpScreen.OnBoardingScreen.route){
            OnBoardingScn(navController = navController)
        }
        composable(route = StartUpScreen.MainScreen.route){
            Home(viewModelForApi)
        }
        composable(route = StartUpScreen.DetailScreen.route){
            DetailScn()
        }
    }
}