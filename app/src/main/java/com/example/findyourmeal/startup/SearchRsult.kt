package com.example.findyourmeal.startup

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.findyourmeal.R
import com.example.findyourmeal.viewmodel.MainViewModelForApi

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchResult(
    searchResult: String,
    viewModelForApi: MainViewModelForApi,
    searchBy: String,
    navController: NavController
) {
    if (searchBy.equals("Area", true)) {

        if (viewModelForApi.searchMealByAreaList.isNullOrEmpty()) {
            LaunchedEffect(key1 = true) {
                viewModelForApi.getSearchMealByArea(searchResult)
            }
        } else {
            val searchByArea = viewModelForApi.searchMealByAreaList
            viewModelForApi.getSearchMealByArea(searchResult)
            val scrollState = rememberScrollState()
            Column(modifier = Modifier.verticalScroll(scrollState)) {
                Scaffold(
                    topBar = {
                        TopAppBar(title = {
                            Text(
                                text = "${stringResource(id = R.string.result)} $searchBy",
                                modifier = Modifier
                                    .padding(start = 20.dp),
                                fontFamily = FontFamily.Serif
                            )
                        },
                            navigationIcon = {
                                IconButton(onClick = { navController.popBackStack() }) {
                                    Icon(
                                        imageVector = Icons.Outlined.ArrowBack,
                                        contentDescription = "navigate back"
                                    )
                                }
                            })

                    }
                ) { paddingValues ->
                    LazyColumn(
                        modifier = Modifier
                            .padding(paddingValues)
                            .fillMaxSize()
                            .height(LocalView.current.height.dp)
                    ) {
                        items(searchByArea!!) { item ->
                            Card(modifier = Modifier
                                .padding(all = 10.dp)
                                .fillMaxSize()) {
                                Row(
                                    modifier = Modifier.clickable {
                                        navController.navigate(
                                            StartUpScreen.DetailScreen.route.replace(
                                                "{$MEAL}",
                                                "${item?.idMeal!!.toInt()}"
                                            )
                                        )
                                    }
                                ) {
                                    AsyncImage(
                                        model = item?.strMealThumb,
                                        contentDescription = "area image",
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier
                                            .size(180.dp)
                                            .padding(all = 10.dp)
                                            .clip(MaterialTheme.shapes.medium)

                                    )
                                    Column(modifier = Modifier.fillMaxSize()) {
                                        Text(
                                            text = "Meal Name : ${item?.strMeal}",
                                            modifier = Modifier.padding(all = 10.dp),
                                            textAlign = TextAlign.Start
                                        )
                                        Spacer(modifier = Modifier.padding(vertical = 10.dp))
                                        Text(
                                            text = "Meal Id : ${item?.idMeal}",
                                            modifier = Modifier.padding(all = 10.dp),
                                            textAlign = TextAlign.Start
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

    } else if (searchBy.equals("Category", true)) {

        if (viewModelForApi.filterByCategory.isNullOrEmpty()) {
            LaunchedEffect(key1 = true) {
                viewModelForApi.getMealByCategory(searchResult)
            }
        } else {
            viewModelForApi.getMealByCategory(searchResult)
            val searchByMealCategory = viewModelForApi.filterByCategory

            val scrollState = rememberScrollState()
            Scaffold(
                topBar = {
                    TopAppBar(title = {
                        Text(
                            text = "${stringResource(id = R.string.related_category)} $searchBy",
                            modifier = Modifier.padding(start = 20.dp),
                            fontFamily = FontFamily.Serif
                        )
                    },
                        navigationIcon = {
                            IconButton(onClick = { navController.popBackStack() }) {
                                Icon(
                                    imageVector = Icons.Filled.ArrowBack,
                                    contentDescription = "navigate back"
                                )
                            }
                        })
                }
            ) { paddingValues ->
                Column(
                    modifier = Modifier
                        .padding(paddingValues)
                        .verticalScroll(scrollState)
                        .fillMaxSize()
                ) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .height(LocalView.current.height.dp)
                    ) {
                        items(searchByMealCategory!!) { item ->
                            Card(modifier = Modifier
                                .padding(all = 10.dp)
                                .fillMaxSize()               
                                .clickable {
                                    navController.navigate(
                                        StartUpScreen.DetailScreen.route.replace(
                                            "{$MEAL}",
                                            "${item!!.idMeal!!.toInt()}"
                                        )
                                    )
                                }) {
                                Row {
                                    AsyncImage(
                                        model = item?.strMealThumb, contentDescription = "Image",
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier
                                            .size(150.dp)
                                            .padding(10.dp)
                                            .clip(MaterialTheme.shapes.medium)

                                    )
                                    Column(
                                        verticalArrangement = Arrangement.SpaceEvenly,
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Text(
                                            text = "Meal Name : ${item?.strMeal}",

                                            modifier = Modifier.padding(all = 10.dp),
                                            textAlign = TextAlign.Start
                                        )
                                        Spacer(modifier = Modifier.padding(vertical = 10.dp))
                                        Text(
                                            text = "Meal Id : ${item?.idMeal}",
                                            modifier = Modifier.padding(all = 10.dp),
                                            textAlign = TextAlign.Start
                                        )
                                    }
                                }
                            }
                        }

                    }
                }
            }

        }
    } else if (searchBy.equals("FirstLetter", true)) {
        if (
            viewModelForApi.searchMealByFirstLetter.isNullOrEmpty()
        ) {
            LaunchedEffect(key1 = true) {
                viewModelForApi.getSearchMealByFirstLetter(searchResult)
            }
        } else {
            val searchByMealFirstLetter = viewModelForApi.searchMealByFirstLetter
            viewModelForApi.getSearchMealByFirstLetter(searchResult)

            val scrollState = rememberScrollState()

            Scaffold(
                topBar = {
                    TopAppBar(title = {
                        Text(
                            text = "${stringResource(id = R.string.result)} $searchBy",

                            modifier = Modifier.padding(start = 20.dp),
                            fontFamily = FontFamily.Serif
                        )
                    }, navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "navigate back"
                            )
                        }
                    })
                }
            ) { paddingValues ->
                Column(
                    modifier = Modifier
                        .verticalScroll(scrollState)
                        .padding(paddingValues)
                        .fillMaxSize()
                ) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .height(LocalView.current.height.dp)
                    ) {
                        items(searchByMealFirstLetter!!) { item ->
                            Card(modifier = Modifier
                                .padding(all = 10.dp)
                                .fillMaxSize()
                                .clickable {
                                    navController.navigate(
                                        StartUpScreen.DetailScreen.route.replace(
                                            "{$MEAL}",
                                            "${item!!.idMeal!!.toInt()}"
                                        )
                                    )
                                }) {
                                Row {
                                    AsyncImage(
                                        model = item?.strMealThumb, contentDescription = "Image",
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier
                                            .size(150.dp)
                                            .padding(10.dp)
                                            .clip(MaterialTheme.shapes.medium)
                                    )
                                    Column(
                                        verticalArrangement = Arrangement.SpaceEvenly,
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Text(
                                            text = "Meal Name : ${item?.strMeal}",
                                            modifier = Modifier.padding(all = 10.dp),
                                            textAlign = TextAlign.Start
                                        )
                                        Spacer(modifier = Modifier.padding(vertical = 10.dp))
                                        Text(
                                            text = "Meal Id : ${item?.idMeal}",
                                            modifier = Modifier.padding(all = 10.dp),
                                            textAlign = TextAlign.Start
                                        )
                                    }
                                }
                            }
                        }

                    }
                }
            }
        }
    } else if (searchBy.equals("MealName", true)) {
        if (viewModelForApi.searchMealByName.isNullOrEmpty()) {
            LaunchedEffect(key1 = true) {
                viewModelForApi.searchMealByName(searchResult)
            }
        } else {
            viewModelForApi.searchMealByName(searchResult)
            val searchByMealName = viewModelForApi.searchMealByName

            val scrollState = rememberScrollState()
            Scaffold(
                topBar = {
                    TopAppBar(title = {
                        Text(
                            text = "${stringResource(id = R.string.result)} $searchBy",
                            modifier = Modifier.padding(start = 20.dp),
                            fontFamily = FontFamily.Serif
                        )
                    },
                        navigationIcon = {
                            IconButton(onClick = { navController.popBackStack() }) {
                                Icon(
                                    imageVector = Icons.Filled.ArrowBack,
                                    contentDescription = "navigate back"
                                )
                            }
                        })
                }
            ) { paddingValues ->
                Column(
                    modifier = Modifier
                        .padding(paddingValues)
                        .verticalScroll(scrollState)
                        .fillMaxSize()
                ) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .height(LocalView.current.height.dp)
                    ) {
                        items(searchByMealName!!) { item ->
                            Card(modifier = Modifier
                                .padding(all = 10.dp)
                                .fillMaxSize()
                                .clickable {
                                    navController.navigate(
                                        StartUpScreen.DetailScreen.route.replace(
                                            "{$MEAL}",
                                            "${item.idMeal!!.toInt()}"
                                        )
                                    )
                                }) {
                                Row {
                                    AsyncImage(
                                        model = item.strMealThumb, contentDescription = "Image",
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier
                                            .size(150.dp)
                                            .padding(10.dp)
                                            .clip(MaterialTheme.shapes.medium)

                                    )
                                    Column(
                                        verticalArrangement = Arrangement.SpaceEvenly,
                                    ) {
                                        Text(
                                            text = "Meal Name : ${item.strMeal}",
                                            modifier = Modifier.padding(all = 10.dp),
                                            textAlign = TextAlign.Start
                                        )
                                        Spacer(modifier = Modifier.padding(vertical = 10.dp))
                                        Text(
                                            text = "Meal Id : ${item.idMeal}",
                                            modifier = Modifier.padding(all = 10.dp),
                                            textAlign = TextAlign.Start
                                        )
                                    }
                                }
                            }
                        }

                    }
                }
            }
        }
    }
}