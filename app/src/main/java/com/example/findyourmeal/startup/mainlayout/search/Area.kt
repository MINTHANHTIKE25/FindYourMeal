package com.example.findyourmeal.startup.mainlayout.search

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.findyourmeal.startup.SEARCH_BY
import com.example.findyourmeal.startup.StartUpScreen
import com.example.findyourmeal.startup.TEXT
import com.example.findyourmeal.viewmodel.MainViewModelForApi

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Area(
    navController: NavController,
    viewModelForApi: MainViewModelForApi,
) {
    val scrollState = rememberScrollState()
    val list = viewModelForApi.allAreaName
    viewModelForApi.getAllAreaNames()
    Column(
        modifier = Modifier.verticalScroll(scrollState)
    ) {
        Text(
            text = "Search with Area", fontSize = 20.sp,
            fontFamily = FontFamily.SansSerif,
            modifier = Modifier.padding(start = 20.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .height(600.dp)
                .padding(bottom = 65.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            items(list!!) { item ->

                OutlinedCard(
                    onClick = {
                        navController.navigate(
                            StartUpScreen.SearchResult.route.replace(
                                "{$TEXT}/{$SEARCH_BY}",
                                "${item?.strArea}/Area"
                            )
                        )
                    },
                    interactionSource = MutableInteractionSource(),
                    modifier = Modifier.padding(horizontal = 20.dp)
                ) {
                    Text(
                        text = item?.strArea!!,
                        fontFamily = FontFamily.Serif,
                        modifier = Modifier.align(CenterHorizontally)
                    )
                }
            }
        }
    }


}