package com.example.findyourmeal.startup.mainlayout

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddTask
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.asLiveData
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.findyourmeal.R
import com.example.findyourmeal.model.allcategories.Category
import com.example.findyourmeal.room.SavedDataViewModel
import com.example.findyourmeal.room.SavedDataViewModelFactory
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.findyourmeal.room.SavedData

@Composable
fun FavScn(
    navController: NavController,
    factory: SavedDataViewModelFactory,
    viewModel: SavedDataViewModel = viewModel(factory = factory)
) {
    val allSavedData = viewModel.allSavedData.collectAsState(initial = emptyList())
    LazyColumn {
        items(allSavedData.value) { item: SavedData ->
            EachFav(mealId = item.mealId, mealTitle = item.title)
        }
    }
//    allSavedData.value.forEach { savedData ->
//        EachFav(savedData.mealId, savedData.title)
//    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun EachFav(mealId: String, mealTitle: String) {
    var visibility by remember {
        mutableStateOf(false)
    }
    Card(
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(10.dp),
        modifier = Modifier
            .padding(horizontal = 15.dp, vertical = 15.dp)
            .fillMaxWidth(1f)
            .height(100.dp)
            .combinedClickable(
                onLongClick = { visibility = !visibility }
            ) {}
    ) {
        Row {
            Box(modifier = Modifier.fillMaxWidth(0.4f)){
                Image(
                    painter = painterResource(id = R.drawable.eat_img), contentDescription = null,
                )
                androidx.compose.animation.AnimatedVisibility(
                    visible = visibility,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    IconButton(onClick = {  }) {
                        Icon(imageVector = Icons.Rounded.AddTask, contentDescription = null)
                    }
                }

            }
            Column(
                modifier = Modifier
                    .padding(start = 5.dp)
                    .fillMaxWidth(0.6f)
            ) {
                Text(text = mealTitle)
                Text(text = mealId)
            }
        }
    }
}