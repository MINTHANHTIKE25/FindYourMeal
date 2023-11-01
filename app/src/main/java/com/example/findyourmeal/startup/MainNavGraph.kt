package com.example.findyourmeal.startup

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.findyourmeal.room.SavedDataViewModelFactory
import com.example.findyourmeal.startup.mainlayout.mainscreen.Home
import com.example.findyourmeal.startup.onboarding.OnBoardingScn
import com.example.findyourmeal.viewmodel.MainViewModelForApi

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun MainNavSetup(navController: NavHostController, viewModelForApi: MainViewModelForApi,
                 factory: SavedDataViewModelFactory) {

    NavHost(navController = navController, startDestination = StartUpScreen.SplashScreen.route) {
        composable(route = StartUpScreen.SplashScreen.route) {
            SplashScn(navController = navController)
        }
        composable(route = StartUpScreen.OnBoardingScreen.route) {
            OnBoardingScn(navController = navController)
        }
        composable(route = StartUpScreen.MainScreen.route) {
            Home(viewModelForApi,factory)
        }
        composable(route = StartUpScreen.DetailScreen.route,
            arguments = listOf(
                navArgument(MEAL) {
                    type = NavType.IntType
                }
            )
        ) {
            val getInt = it.arguments?.getInt(MEAL)

            DetailScn(getInt!!, viewModelForApi, navController)

        }
    }
}