package com.example.findyourmeal.startup

sealed class StartUpScreen(val route:String){
    object SplashScreen:StartUpScreen(route = "splash_screen")
    object OnBoardingScreen:StartUpScreen(route = "onboarding_screen")
    object MainScreen:StartUpScreen(route = "main_screen")
    object DetailScreen:StartUpScreen(route = "detail_screen")
}
