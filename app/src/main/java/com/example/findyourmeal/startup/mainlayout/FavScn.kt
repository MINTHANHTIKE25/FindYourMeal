package com.example.findyourmeal.startup.mainlayout

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CheckCircleOutline
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
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
import androidx.navigation.NavController
import com.example.findyourmeal.R
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
    var selected by remember { mutableStateOf(false) }
    LazyColumn(
        modifier = Modifier.padding(bottom = 70.dp)
    ) {
        if (selected){
            item {
                Row {
                    Text(text = "Settings", modifier = Modifier.weight(1f))
                    Box(modifier = Modifier.weight(2f)){
                        Row(modifier = Modifier.align(Alignment.CenterEnd)) {
                            Text(text = "Select All")
                            IconButton(onClick = { /*TODO*/ }) {
                                
                            }
                        }
                    }
                }
            }
        }
        items(allSavedData.value) { item: SavedData ->
            EachFav(
                mealId = item.mealId, mealTitle = item.title,
                isAllSelected = { selected = !selected },
                visibilityOfLongClick = selected
            )
        }
    }
}
//    allSavedData.value.forEach { savedData ->
//        EachFav(savedData.mealId, savedData.title)
//    }

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun EachFav(
    mealId: String,
    mealTitle: String,
    isAllSelected: () -> Unit,
    visibilityOfLongClick: Boolean
) {
    Card(
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(10.dp),
        modifier = Modifier
            .padding(horizontal = 15.dp, vertical = 15.dp)
            .height(100.dp)
            .combinedClickable(
                onLongClick = { isAllSelected() }
//                onClick = { visibilityOfClick = !visibilityOfClick }
            ) {}
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(id = R.drawable.eat_img),
                contentDescription = null,
            )
            Box(modifier = Modifier.fillMaxWidth()) {
                if (visibilityOfLongClick) {
                    androidx.compose.animation.AnimatedVisibility(
                        visible = visibilityOfLongClick,
                        enter = fadeIn(),
                        exit = fadeOut(),
                        modifier = Modifier.align(Alignment.TopEnd)
                    ) {
                        IconButton(onClick = isAllSelected) {
                            Icon(
                                imageVector = Icons.Rounded.CheckCircleOutline,
                                contentDescription = null
                            )
                        }
                    }
                }
                Column(
                    modifier = Modifier
                        .padding(start = 5.dp)
                ) {
                    Text(text = mealTitle)
                    Text(text = mealId)
                }
            }
        }
    }
}