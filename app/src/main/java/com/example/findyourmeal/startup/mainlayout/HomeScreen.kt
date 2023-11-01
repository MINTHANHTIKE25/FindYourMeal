package com.example.findyourmeal.startup.mainlayout

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.findyourmeal.R
import com.example.findyourmeal.model.allcategories.Category
import com.example.findyourmeal.room.SavedData
import com.example.findyourmeal.room.SavedDataViewModel
import com.example.findyourmeal.room.SavedDataViewModelFactory
import com.example.findyourmeal.startup.categorydialog.CustomDialogForCategory
import com.example.findyourmeal.viewmodel.MainViewModelForApi

@Composable
fun HomeScreen(
    navController: NavController, viewModelForApi: MainViewModelForApi,
    factory: SavedDataViewModelFactory, vmOfRoom: SavedDataViewModel = viewModel(factory = factory)
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.animation_for_home))
    val loading by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading))
    val allCategory: List<Category> = viewModelForApi.allCategories
    LaunchedEffect(Unit) {
        viewModelForApi.getAllCategories()
    }
    val scrollState = rememberScrollState()
    Column(
        horizontalAlignment = CenterHorizontally,
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

        if (allCategory.isEmpty()) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = CenterHorizontally
            ) {
                LottieAnimation(
                    modifier = Modifier.size(200.dp),
                    enableMergePaths = true,
                    speed = 2f,
                    composition = loading,
                    reverseOnRepeat = true,
                )
            }
        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(25.dp),
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .height(1000.dp)
                .fillMaxWidth()
        ) {

            items(allCategory) { item: Category ->
                EachCategory(
                    category = item,vmOfRoom
                )
            }
        }
    }
}

@Composable
fun EachCategory(
    category: Category,
    vmOfRoom: SavedDataViewModel
) {

    //Creating dialog to show the information of the category
    val showDialog = remember { mutableStateOf(false) }
    if (showDialog.value) {
        CustomDialogForCategory(
            categoryName = category.strCategory,
            img = category.strCategoryThumb,
            description = category.strCategoryDescription
        ) {
            showDialog.value = it
        }
    }

    //Creating Item layout
    Card(
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(pressedElevation = 15.dp),
        modifier = Modifier
            .wrapContentSize()
            .clickable {
                showDialog.value = true
                vmOfRoom.insertData(SavedData(mealId = category.idCategory, title = category.strCategory))
            }
            .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
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