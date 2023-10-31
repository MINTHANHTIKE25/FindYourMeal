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

        }
        composable(route = StartUpScreen.OnBoardingScreen.route){

        }
        composable(route = StartUpScreen.MainScreen.route){

        }
        composable(route = StartUpScreen.DetailScreen.route){

        }
    }
}