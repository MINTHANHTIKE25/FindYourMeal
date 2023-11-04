package com.example.findyourmeal.startup.mainlayout

import android.opengl.Visibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
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
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckBox
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.SelectAll
import androidx.compose.material.icons.outlined.CheckBoxOutlineBlank
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissState
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.findyourmeal.R
import com.example.findyourmeal.room.SavedData
import com.example.findyourmeal.room.SavedDataViewModel
import com.example.findyourmeal.room.SavedDataViewModelFactory
import com.example.findyourmeal.startup.MEAL
import com.example.findyourmeal.startup.StartUpScreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavScn(
    navController: NavController,
    factory: SavedDataViewModelFactory,
    viewModelFromRoom: SavedDataViewModel = viewModel(factory = factory)
) {
    var isLongCLick by rememberSaveable { mutableStateOf(false) }
    val allSavedData = viewModelFromRoom.allSavedData.collectAsState(initial = emptyList())
//    val allSavedMutable= allSavedData.value.toMutableStateList()
    var selectAllClick by rememberSaveable { mutableStateOf(false) }
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
                        text = stringResource(id = R.string.your_fav),
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

                                IconButton(onClick = { selectAllClick = !selectAllClick }) {
                                    Icon(
                                        imageVector = if (selectAllClick) {
                                            Icons.Filled.SelectAll
                                        } else {
                                            Icons.Outlined.CheckBoxOutlineBlank
                                        },
                                        contentDescription = null
                                    )
                                }
                                if (!selectAllClick) {
                                    Text(text = "Select All")
                                } else {
                                    IconButton(onClick = { viewModelFromRoom.deleteAll() }) {
                                        Icon(
                                            imageVector = Icons.Default.Delete,
                                            contentDescription = null
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }

        }
        items(items = allSavedData.value,
            key = { item: SavedData -> item.tbId })
        { item: SavedData ->
            val currentItem by rememberUpdatedState(item)
            val dismissState = rememberDismissState(
                confirmValueChange = {
                    if (it == DismissValue.DismissedToStart || it == DismissValue.DismissedToEnd) {
                        viewModelFromRoom.deleteSavedData(currentItem)
                        true
                    } else false
                }
            )
            if (dismissState.isDismissed(DismissDirection.EndToStart) ||
                dismissState.isDismissed(DismissDirection.StartToEnd)
            ) {
                viewModelFromRoom.deleteSavedData(item)
            }
            SwipeToDismiss(
                state = dismissState,
                //this background is the background of items when we swipe
                background = {
                    SwipeBackground(dismissState = dismissState)
                },
                dismissContent = {
                    EachFav(
                        mealId = item.mealId, isSelectAll = selectAllClick,
                        isLongClick = isLongCLick,
                        mealTitle = item.title,
                        onLongClicking = { isLongCLick = !isLongCLick },
                        photosUrl = item.photosUrl,
                        navController = navController
                    )
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
    photosUrl: String,
    navController: NavController
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
            ) {
                navController.navigate(
                    StartUpScreen.DetailScreen.route.replace(
                        "{$MEAL}",
                        "${mealId.toInt()}"
                    )
                )
            }
    ) {
        Row {
            Box(modifier = Modifier.weight(0.4f)) {
                AsyncImage(
                    model = photosUrl,
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds
                )
            }
            Box(
                modifier = Modifier
                    .padding(start = 5.dp)
                    .weight(0.6f)
            ) {
                if (isLongClick) {
                    if (isSelectAll) {
                        ShowHide(visibility = true, modifier = Modifier.align(Alignment.TopEnd))
                    } else {
                        ShowHide(visibility = false, modifier = Modifier.align(Alignment.TopEnd))
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

@Composable
fun ShowHide(visibility: Boolean, modifier: Modifier) {
    androidx.compose.animation.AnimatedVisibility(
        visible = visibility,
        enter = fadeIn(),
        exit = fadeOut(),
        modifier = modifier
    ) {
        Icon(
            imageVector = Icons.Default.CheckBox,
            contentDescription = null
        )
    }
}

@Composable
@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
fun SwipeBackground(dismissState: DismissState) {
    val direction = dismissState.dismissDirection ?: return
    val color by animateColorAsState(
        when (dismissState.targetValue) {
            DismissValue.Default -> Color.LightGray
            DismissValue.DismissedToEnd -> Color.Red
            DismissValue.DismissedToStart -> Color.Red
        }, label = ""
    )
    val alignment = when (direction) {
        DismissDirection.StartToEnd -> Alignment.CenterStart
        DismissDirection.EndToStart -> Alignment.CenterEnd
    }
    val icon = when (direction) {
        DismissDirection.StartToEnd -> Icons.Default.Delete
        DismissDirection.EndToStart -> Icons.Default.Delete
    }
    val scale by animateFloatAsState(
        if (dismissState.targetValue == DismissValue.Default) 0.75f else 1f, label = ""
    )

    Box(
        Modifier
            .fillMaxSize()
            .background(color)
            .padding(horizontal = 20.dp),
        contentAlignment = alignment
    ) {
        Icon(
            icon,
            contentDescription = "Localized description",
            modifier = Modifier.scale(scale)
        )
    }
}