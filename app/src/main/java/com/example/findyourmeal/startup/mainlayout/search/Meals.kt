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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.findyourmeal.R
import com.example.findyourmeal.connectivity.ConnectivityObserver
import com.example.findyourmeal.startup.SEARCH_BY
import com.example.findyourmeal.startup.StartUpScreen
import com.example.findyourmeal.startup.TEXT
import com.example.findyourmeal.viewmodel.MainViewModelForApi

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Meals(
    navController: NavController,
    viewModelForApi: MainViewModelForApi,
    status: ConnectivityObserver.Status
) {
    val scrollState = rememberScrollState()

    viewModelForApi.searchMealByName
    viewModelForApi.searchMealByName("")

    val mealList = viewModelForApi.searchMealByName
    val shimmer by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.shimmer_loading))
    if (status.toString().equals(ConnectivityObserver.Status.Available.toString(), true)) {
        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .fillMaxSize()
        ) {
            Text(
                text = stringResource(id = R.string.filter_by_category), fontSize = 20.sp,
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
                            .padding(horizontal = 20.dp, vertical = 10.dp)
                            .fillMaxWidth()
                    ) {
                        Row {
                            AsyncImage(
                                model = item.strMealThumb, contentDescription = "image",
                                modifier = Modifier
                                    .size(150.dp)
                                    .padding(10.dp)
                                    .clip(MaterialTheme.shapes.medium)
                            )
                            Column(
                                modifier = Modifier
                                    .padding(all = 10.dp)
                                    .fillMaxSize(),
                                verticalArrangement = Arrangement.SpaceEvenly

                            ) {
                                Text(
                                    text = "Meal name : ${item.strArea!!}",
                                    fontFamily = FontFamily.Serif,
                                    modifier = Modifier.padding(start = 10.dp, top = 10.dp),
                                    textAlign = TextAlign.Start
                                )
                                Text(
                                    text = "Meal Id : ${item.idMeal!!}",
                                    fontFamily = FontFamily.Serif,
                                    modifier = Modifier.padding(start = 10.dp, top = 10.dp),
                                    textAlign = TextAlign.Start

                                )
                            }
                        }

                    }
                }
            }
        }
    } else if (status.toString().equals(
            ConnectivityObserver.Status.Unavailable.toString(),
            true
        ) || status.toString().equals(ConnectivityObserver.Status.Lost.toString(), true) ||
        status.toString().equals(ConnectivityObserver.Status.Losing.toString(), true) ||
        status.toString().equals(ConnectivityObserver.Status.Loading.toString(), true)
    ) {
        Column {
            LottieAnimation(
                composition = shimmer,
                modifier = Modifier
                    .wrapContentHeight(),
                restartOnPlay = true,
                iterations = LottieConstants.IterateForever,
                contentScale = ContentScale.FillBounds
            )
            LottieAnimation(
                composition = shimmer,
                modifier = Modifier
                    .wrapContentHeight(),
                restartOnPlay = true,
                iterations = LottieConstants.IterateForever,
                contentScale = ContentScale.FillBounds
            )
        }
    }

}