package com.example.findyourmeal.startup.mainlayout.mainscreen

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.findyourmeal.connectivity.ConnectivityObserver
import com.example.findyourmeal.savinginmemory.SharedPrefManager
import com.example.findyourmeal.startup.mainlayout.FavScn
import com.example.findyourmeal.startup.mainlayout.HomeScreen
import com.example.findyourmeal.startup.mainlayout.SettingScn
import com.example.findyourmeal.startup.mainlayout.search.SearchScn
import com.example.findyourmeal.viewmodel.LocaleViewModel
import com.example.findyourmeal.viewmodel.MainViewModelForApi

//Setting navigation host for main screen
@Composable
fun MainScnNavGraph(
    viewModelForApi: MainViewModelForApi, navController: NavHostController,
//    factory: SavedDataViewModelFactory,
    mainNavController: NavController, context: Context,
    status: ConnectivityObserver.Status
) {

    NavHost(navController = navController, startDestination = BottomNaviRoutes.HomeScreen.routes) {
        composable(BottomNaviRoutes.HomeScreen.routes) {
            HomeScreen(
                viewModelForApi,
//                factory,
                status = status)
        }
        composable(BottomNaviRoutes.SearchScreen.routes) {
            SearchScn(mainNavController, viewModelForApi, status)
        }
        composable(BottomNaviRoutes.FavScreen.routes) {
            FavScn(
                mainNavController,
//                factory
            )
        }
        composable(BottomNaviRoutes.SettingScreen.routes) {
            SettingScn(
                navController = navController,
                localeViewModel = LocaleViewModel(),
                sharedPrefManager = SharedPrefManager(context)
            )

        }
    }
}