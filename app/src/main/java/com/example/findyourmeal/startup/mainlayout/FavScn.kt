package com.example.findyourmeal.startup.mainlayout

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckBox
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.outlined.CheckBoxOutlineBlank
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.findyourmeal.R
import com.example.findyourmeal.room.SavedDataViewModel
import com.example.findyourmeal.room.SavedDataViewModelFactory
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.findyourmeal.room.SavedData
import kotlinx.coroutines.selects.select


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavScn(
    navController: NavController,
    factory: SavedDataViewModelFactory,
    viewModel: SavedDataViewModel = viewModel(factory = factory)
) {
    var isLongCLick by rememberSaveable { mutableStateOf(false) }
    val allSavedData = viewModel.allSavedData.collectAsState(initial = emptyList())
    val allData = allSavedData.value.toMutableList()
    var selectAllClick by rememberSaveable { mutableStateOf(false) }
    BackHandler(true) {
        isLongCLick = !isLongCLick
    }
    LazyColumn(
        modifier = Modifier.padding(bottom = 70.dp),
        state = rememberLazyListState()
    ) {
        item {
            Card(
                modifier = Modifier.wrapContentSize(),
                shape = RoundedCornerShape(bottomStart = 15.dp, bottomEnd = 15.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.height(60.dp)
                ) {
                    Text(
                        text = "Your favourite",
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 14.dp)
                    )
                    Box {
                        Row(
                            modifier = Modifier
                                .align(Alignment.CenterEnd)
                                .padding(end = 5.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            if (isLongCLick) {
                                Text(text = "Select All")
                                IconButton(onClick = { selectAllClick = !selectAllClick }) {
                                    Icon(
                                        imageVector = if (selectAllClick) {
                                            Icons.Filled.CheckBox
                                        } else {
                                            Icons.Outlined.CheckBoxOutlineBlank
                                        },
                                        contentDescription = null
                                    )
                                }
                            }
                        }
                    }
                }
            }

        }

        items(allData) { item: SavedData ->
            val state = rememberDismissState(
                confirmValueChange = {
                    when (it) {
                        DismissValue.DismissedToStart -> {}
                        DismissValue.DismissedToEnd -> {}
                        DismissValue.Default -> {}
                    }
                    if (it == DismissValue.DismissedToStart || it == DismissValue.DismissedToEnd) {
                        allData.remove(item)
                        viewModel.deleteSavedData(item)
                    }
                    true
                }
            )
            SwipeToDismiss(
                state = state,
                background = {
                    val color = when (state.dismissDirection) {
                        DismissDirection.EndToStart -> Color.Red
                        DismissDirection.StartToEnd -> Color.Red
                        null -> Color.Transparent
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = color)
                            .padding(vertical = 40.dp, horizontal = 8.dp)
                    )
                },
                directions = setOf(DismissDirection.StartToEnd, DismissDirection.EndToStart),
                dismissContent = {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        elevation = CardDefaults.cardElevation(
                            animateDpAsState(
                                if (state.dismissDirection != null) 4.dp else 0.dp, label = ""
                            ).value
                        )
                    )
                    {
                        EachFav(
                            mealId = item.mealId, isSelectAll = selectAllClick,
                            isLongClick = isLongCLick,
                            mealTitle = item.title,
                            onLongClicking = { isLongCLick = !isLongCLick },
                            item = item,
                            allSavedData.value.size
                        )
                    }

                })

        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun EachFav(
    mealId: String,
    isSelectAll: Boolean,
    isLongClick: Boolean,
    mealTitle: String,
    onLongClicking: () -> Unit,
    item: SavedData,
    savedDataAmt: Int
) {
    var isLongOnClick by rememberSaveable { mutableStateOf(false) }
    Card(
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(10.dp),
        modifier = Modifier
            .padding(horizontal = 15.dp, vertical = 15.dp)
            .fillMaxWidth(1f)
            .height(100.dp)
            .combinedClickable(
                onLongClick = {
                    onLongClicking();
                    isLongOnClick = !isLongClick
                }
            ) { }
    ) {
        Row {
            Box(modifier = Modifier.weight(0.4f)) {
                Image(
                    painter = painterResource(id = R.drawable.eat_img), contentDescription = null,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Box(
                modifier = Modifier
                    .padding(start = 5.dp)
                    .weight(0.6f)
            ) {
                if (isLongClick) {
                    if (isSelectAll) {
                        androidx.compose.animation.AnimatedVisibility(
                            visible = true,
                            enter = fadeIn(),
                            exit = fadeOut(),
                            modifier = Modifier.align(Alignment.TopEnd)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = null
                            )
                        }
                    } else {
                        androidx.compose.animation.AnimatedVisibility(
                            visible = false,
                            enter = fadeIn(),
                            exit = fadeOut(),
                            modifier = Modifier.align(Alignment.TopEnd)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = null
                            )
                        }
                    }


                }
                Column {
                    Text(text = mealTitle)
                    Text(text = mealId)
                }

            }
        }
    }
}

