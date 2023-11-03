package com.example.findyourmeal.startup.mainlayout.search

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.findyourmeal.startup.SEARCH_BY
import com.example.findyourmeal.startup.StartUpScreen
import com.example.findyourmeal.startup.TEXT
import com.example.findyourmeal.viewmodel.MainViewModelForApi

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Meals(
    navController: NavController, viewModelForApi: MainViewModelForApi
) {

    if (viewModelForApi.searchMealByName.isNullOrEmpty()) {
        LaunchedEffect(key1 = true) {
            viewModelForApi.searchMealByName("")
        }
    } else {
        val scrollState = rememberScrollState()
        val mealList = viewModelForApi.searchMealByName
        viewModelForApi.searchMealByName("")
        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .fillMaxSize()
        ) {
            Text(
                text = "Meal List", fontSize = 20.sp,
                fontFamily = FontFamily.SansSerif,
                modifier = Modifier.padding(start = 20.dp)
            )

            LazyColumn(
                modifier = Modifier
                    .height(1000.dp)
                    .padding(bottom = 65.dp),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(mealList!!) { item ->
                    OutlinedCard(
                        onClick = {
                            navController.navigate(
                                StartUpScreen.SearchResult.route.replace(
                                    "{$TEXT}/{$SEARCH_BY}",
                                    "${item.strCategory}/Category"
                                )
                            )
                        },
                        interactionSource = MutableInteractionSource(),
                        modifier = Modifier
                            .padding(horizontal = 20.dp, vertical = 20.dp)
                            .fillMaxWidth()
                    ) {
                        Row {
                            AsyncImage(
                                model = item.strMealThumb, contentDescription = "image",
                                modifier = Modifier.size(180.dp)
                            )
                            Column {
                                Text(
                                    text = item.strArea!!,
                                    fontFamily = FontFamily.Serif,
                                    modifier = Modifier.padding(all = 10.dp)
                                )
                                Text(
                                    text = item.idMeal!!, fontFamily = FontFamily.Serif,
                                    modifier = Modifier.padding(all = 10.dp)
                                )
                            }
                        }

                    }
                }
            }
        }
    }
}