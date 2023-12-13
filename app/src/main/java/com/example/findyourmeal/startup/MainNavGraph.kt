package com.example.findyourmeal.startup

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.findyourmeal.connectivity.ConnectivityObserver
import com.example.findyourmeal.savinginmemory.SharedPrefManager
import com.example.findyourmeal.startup.mainlayout.detailscreen.DetailScn
import com.example.findyourmeal.startup.mainlayout.mainscreen.Home
import com.example.findyourmeal.startup.onboarding.OnBoardingScn
import com.example.findyourmeal.viewmodel.MainViewModelForApi

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun MainNavSetup(
    viewModelForApi: MainViewModelForApi= hiltViewModel(),
    navController: NavHostController,
//    factory: SavedDataViewModelFactory,
    status : ConnectivityObserver.Status,
    sharedPrefManager: SharedPrefManager
) {

    NavHost(navController = navController, startDestination = StartUpScreen.SplashScreen.route) {
        composable(route = StartUpScreen.SplashScreen.route) {
            SplashScn(navController = navController,sharedPrefManager)
        }
        composable(route = StartUpScreen.OnBoardingScreen.route) {
            OnBoardingScn(navController = navController)
        }
        composable(route = StartUpScreen.MainScreen.route) {
            Home(
                viewModelForApi=viewModelForApi,
//                factory,
                navController,
                LocalContext.current,status)
        }
        composable(route = StartUpScreen.DetailScreen.route,
            arguments = listOf(
                navArgument(MEAL) {
                    type = NavType.IntType
                },
                navArgument(ISFAV){
                    type=NavType.BoolType
                }
            )
        ) {
            val getInt = it.arguments?.getInt(MEAL)
            val getIsFav= it.arguments?.getBoolean(ISFAV)
            DetailScn(
                getIsFav!!,getInt!!,
                viewModelForApi,
                navController,
//                factory,
                status = status)

        }
        composable(route = StartUpScreen.SearchResult.route,
            arguments = listOf(
                navArgument(TEXT) {
                    type = NavType.StringType
                },
                navArgument(SEARCH_BY) {
                    type = NavType.StringType
                }
            )
        ) {
            val searchBy = it.arguments?.getString(SEARCH_BY)
            val searchResult = it.arguments?.getString(TEXT)
            SearchResult(searchResult!!, viewModelForApi,searchBy!!,navController,status)
        }
    }
}