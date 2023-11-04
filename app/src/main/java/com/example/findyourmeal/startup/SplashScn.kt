package com.example.findyourmeal.startup

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.findyourmeal.R
import com.example.findyourmeal.savinginmemory.SharedPrefManager
import kotlinx.coroutines.delay

/**
 * This composable is for splash screen
 */
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun SplashScn(navController: NavController, sharedPrefManager: SharedPrefManager) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.splash_animation))
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AnimatedVisibility(
            visible = true,
            enter = slideInHorizontally() + fadeIn()
        ) {
            LottieAnimation(
                speed = 2f,
                composition = composition,
                contentScale = ContentScale.Inside,
                reverseOnRepeat = true
            )
        }
        LaunchedEffect(key1 = true) {
            delay(2000)
            navController.popBackStack()
            navController.navigate(
                getRoute(sharedPrefManager)
            )
        }
    }
}

fun getRoute(sharedPrefManager: SharedPrefManager): String {
    val getData = sharedPrefManager.retrieveBoolean("onboardingDone", true)
    return if (getData) {
        StartUpScreen.MainScreen.route
    } else {
        StartUpScreen.OnBoardingScreen.route
    }
}