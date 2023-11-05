package com.example.findyourmeal.startup.onboarding

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.findyourmeal.savinginmemory.SharedPrefManager
import com.example.findyourmeal.startup.StartUpScreen
import com.example.findyourmeal.ui.theme.md_theme_dark_onPrimaryContainer
import com.example.findyourmeal.ui.theme.md_theme_light_onPrimaryContainer
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnBoardingScn(navController: NavController) {
    val sharedPrefManager = SharedPrefManager(LocalContext.current)
    val pages = listOf(
        OnBoardingPages.First,
        OnBoardingPages.Second,
        OnBoardingPages.Third
    )
    val isDarkMode=SharedPrefManager(LocalContext.current).retrieveBoolean("DARKMODE",false)
    val pagerState = rememberPagerState()

    Column(modifier = Modifier.fillMaxSize()) {
        HorizontalPager(
            count = 3,
            state = pagerState,
            verticalAlignment = Alignment.Top
        ) { position ->
            PagerScreen(onBoardingPages = pages[position])
        }
        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 35.dp),
            activeColor = if (isDarkMode) Color.White else Color.Black,
            inactiveColor = Color.Gray
        )
        FinishButton(modifier = Modifier.padding(top = 15.dp), pagerState = pagerState, isDarkMode = isDarkMode) {
            navController.popBackStack()
            navController.navigate(StartUpScreen.MainScreen.route)
            sharedPrefManager.saveBoolean("onboardingDone", true)
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun FinishButton(modifier: Modifier, pagerState: PagerState,isDarkMode:Boolean, onClick: () -> Unit) {
    Row(
        modifier = modifier.padding(horizontal = 40.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Center
    ) {
        AnimatedVisibility(
            visible = pagerState.currentPage == 2,
            modifier = modifier.fillMaxWidth()
        ) {
            Button(
                onClick = onClick,
                colors = ButtonDefaults.buttonColors(
                    if (isDarkMode) Color.White else Color.Black
                )
            ) {
                Text(text = "Finish",
                    color = if (isDarkMode) Color.Black else Color.White)
            }
        }
    }
}

@Composable
fun PagerScreen(onBoardingPages: OnBoardingPages) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = onBoardingPages.image),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .fillMaxHeight(0.6f)
        )
        Text(
            text = onBoardingPages.title,
            modifier = Modifier.fillMaxWidth(),
            fontSize = MaterialTheme.typography.h4.fontSize,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Text(
            text = onBoardingPages.description,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
                .padding(top = 20.dp),
            fontSize = MaterialTheme.typography.subtitle1.fontSize,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center
        )
    }
}
