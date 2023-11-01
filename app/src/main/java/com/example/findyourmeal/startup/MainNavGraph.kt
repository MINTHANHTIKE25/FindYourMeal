package com.example.findyourmeal.startup

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.findyourmeal.startup.scnmain.Home
import com.example.findyourmeal.viewmodel.MainViewModelForApi

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
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
//        composable(route = StartUpScreen.DetailScreen.route
//        ){
//            val getdata = it.arguments?.getParcelable(CATEGORY,Category::class.java)
//            DetailScn(meal = z, mainNavController = )
//
//        }
    }
}