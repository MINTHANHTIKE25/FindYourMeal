package com.example.findyourmeal.startup

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun MainNavSetup(navController: NavHostController) {
    NavHost(navController = navController, startDestination = StartUpScreen.SplashScreen.route){
        composable(route = StartUpScreen.SplashScreen.route){
            SplashScn(navController = navController)
        }
        composable(route = StartUpScreen.OnBoardingScreen.route){
            OnBoardingScn()
        }
        composable(route = StartUpScreen.MainScreen.route){
            MainScn()
        }
        composable(route = StartUpScreen.DetailScreen.route){
            DetailScn()
        }
    }
}