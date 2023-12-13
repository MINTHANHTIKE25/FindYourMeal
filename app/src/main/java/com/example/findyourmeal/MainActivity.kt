package com.example.findyourmeal

import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.findyourmeal.connectivity.ConnectivityObserver
import com.example.findyourmeal.connectivity.NetworkConnectivityObserver
import com.example.findyourmeal.savinginmemory.SharedPrefManager
import com.example.findyourmeal.startup.MainNavSetup
import com.example.findyourmeal.ui.theme.FindYourMealTheme
import com.example.findyourmeal.viewmodel.LocaleViewModel
import com.example.findyourmeal.viewmodel.MainViewModelForApi
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {

        //saving the dark mode state
        val sharedPrefDarkMode=SharedPrefManager(this)
        val systemTheme = when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
            Configuration.UI_MODE_NIGHT_YES -> { true }
            Configuration.UI_MODE_NIGHT_NO -> { false }
            else -> { false }
        }
//        val dao=DbCreation.getDataBase(application).dao
//        val repo=SavedDataRepo(dao)
//        val factory=SavedDataViewModelFactory(repo=repo)

        val localeViewModel = LocaleViewModel()
        val sharedPrefManager = SharedPrefManager(this)
        val connectivityObserver = NetworkConnectivityObserver(applicationContext)
        super.onCreate(savedInstanceState)
        setContent {
            sharedPrefDarkMode.saveBoolean("DARKMODE",systemTheme)
            FindYourMealTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val status by connectivityObserver.observe()
                        .collectAsState(initial = ConnectivityObserver.Status.Loading)
                    if (sharedPrefManager.retrieveLanguage(localeViewModel.currentLocale)
                            .equals("en", true)
                    ) {
                        localeViewModel.setLocale(Locale("en"), this, "en")
                    } else {
                        localeViewModel.setLocale(Locale("my"), this, "my")
                    }
                    val navController = rememberNavController()
                    MainNavSetup(
                        navController=navController,
//                        factory = factory,
                        status = status,
                        sharedPrefManager = sharedPrefManager)
                }
            }
        }
    }
}
