@file:OptIn(ExperimentalFoundationApi::class)

package com.example.findyourmeal.startup.mainlayout.search

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.findyourmeal.viewmodel.MainViewModelForApi

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PagingForSearchScreen(
    navController: NavController,
    viewModelForApi: MainViewModelForApi,
) {
    val tabItems = listOf("Area", "Meals")
    val pagerState = rememberPagerState(pageCount = { tabItems.size })
    val selectedTabIndex = remember {
        mutableIntStateOf(0)
    }

    LaunchedEffect(selectedTabIndex.intValue) {
        pagerState.animateScrollToPage(selectedTabIndex.intValue)
    }

    LaunchedEffect(pagerState.currentPage) {
        selectedTabIndex.intValue = pagerState.currentPage
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TabRow(
            selectedTabIndex = selectedTabIndex.intValue,
            modifier = Modifier
                .height(50.dp)
                .background(Color.Unspecified)
        ) {
            tabItems.forEachIndexed { index, s ->
                Tab(
                    selected = selectedTabIndex.intValue == index,
                    onClick = { selectedTabIndex.intValue = index },
                    modifier = Modifier.height(30.dp),
                    interactionSource = MutableInteractionSource()
                )
                {
                    Text(text = s)
                }
            }
        }
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
        ) {
            if (selectedTabIndex.intValue == 0 ){
                Area(navController, viewModelForApi)
            }else{
                Meals(navController, viewModelForApi)
            }


        }
    }
}