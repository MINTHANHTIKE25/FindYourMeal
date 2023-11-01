package com.example.findyourmeal.startup.scnmain

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.findyourmeal.R
import com.example.findyourmeal.model.allcategories.Category
import com.example.findyourmeal.viewmodel.MainViewModelForApi

@Composable
fun HomeScreen(navController: NavController, viewModelForApi: MainViewModelForApi) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.animation_for_home))
    val allCategory: List<Category> = viewModelForApi.allCategories
    LaunchedEffect(Unit) {
        viewModelForApi.getAllCategories()
    }
    val scrollState = rememberScrollState()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .wrapContentSize()
            .verticalScroll(scrollState, true)
            .padding(bottom = 80.dp)
    ) {
        LottieAnimation(
            composition = composition,
            modifier = Modifier.size(200.dp),
            restartOnPlay = true,
            iterations = LottieConstants.IterateForever
        )

//        items(allCategory){ }
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(25.dp),
            horizontalArrangement = Arrangement.spacedBy(5.dp),
            modifier = Modifier.height(1000.dp)
        ) {
            if (allCategory.isEmpty()) {
                item { Text(text = "text loading") }
            }
            items(allCategory) { item: Category ->
                EachCategory(
                    category = item,
                    viewModelForApi = viewModelForApi,
                    navController = navController
                )
            }
        }
    }
}
@Composable
fun EachCategory(
    category: Category,
    viewModelForApi: MainViewModelForApi,
    navController: NavController
) {
    Card(
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(pressedElevation = 15.dp),
        modifier = Modifier
            .wrapContentSize()
            .clickable {
//                viewModelForApi.addMeal(meal)\
//                navController.navigate(HomeScreens.DetailScreen.route)
            }
            .padding(start = 10.dp, end = 10.dp)
            .width(250.dp)
            .height(120.dp)
    ) {
        AsyncImage(
            model = category.strCategoryThumb, contentDescription = null,
            contentScale = ContentScale.Inside, modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 15.dp)
        )
        Text(text = category.strCategory, modifier = Modifier.align(Alignment.CenterHorizontally))
    }
}