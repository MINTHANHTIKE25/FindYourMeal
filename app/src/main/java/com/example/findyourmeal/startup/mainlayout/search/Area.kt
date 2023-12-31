package com.example.findyourmeal.startup.mainlayout.search

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
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
fun Area(
    navController: NavController,
    viewModelForApi: MainViewModelForApi,
    status: ConnectivityObserver.Status
) {
    val scrollState = rememberScrollState()
    var list: List<com.example.findyourmeal.model.listofallarea.Meal?>? by remember {
        mutableStateOf(listOf())
    }
    viewModelForApi.getAllAreaNames()
    list = viewModelForApi.allAreaName

    val shimmer =
        rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.shimmer_loading))


    if (status.toString().equals(ConnectivityObserver.Status.Available.toString(), true)) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            Text(
                text = stringResource(id = R.string.search_with_area), fontSize = 20.sp,
                fontFamily = FontFamily.SansSerif,
                modifier = Modifier.padding(start = 20.dp)
            )

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .height(800.dp)
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
                        modifier = Modifier
                            .padding(horizontal = 20.dp, vertical = 20.dp)
                            .height(30.dp)
                    ) {
                        Text(
                            text = item?.strArea!!,
                            fontFamily = FontFamily.Serif,
                            modifier = Modifier
                                .align(CenterHorizontally)
                                .padding(top = 5.dp),
                            textAlign = TextAlign.Center

                        )
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
                composition = shimmer.value,
                modifier = Modifier
                    .wrapContentHeight(),
                restartOnPlay = true,
                iterations = LottieConstants.IterateForever,
                contentScale = ContentScale.FillBounds
            )
            LottieAnimation(
                composition = shimmer.value,
                modifier = Modifier
                    .wrapContentHeight(),
                restartOnPlay = true,
                iterations = LottieConstants.IterateForever,
                contentScale = ContentScale.FillBounds
            )
        }
    }
}