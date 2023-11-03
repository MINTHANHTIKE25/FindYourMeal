package com.example.findyourmeal.startup

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.findyourmeal.viewmodel.MainViewModelForApi

@Composable
fun SearchResult(
    searchResult: String,
    viewModelForApi: MainViewModelForApi,
    searchBy: String,
    navController: NavController
) {
    Log.d("search by", searchBy)
    Log.d("search Result", searchResult)


    if (searchBy.equals("Area", true)) {
        val searchByArea = viewModelForApi.searchMealByAreaList
        viewModelForApi.getSearchMealByArea(searchResult)
        val scrollState = rememberScrollState()
        Column(modifier = Modifier.verticalScroll(scrollState)) {
            Text(
                text = "You search for $searchBy",
                modifier = Modifier
                    .padding(start = 20.dp),
                fontFamily = FontFamily.Serif
            )
            Spacer(modifier = Modifier.padding(vertical = 7.dp))
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .height(1000.dp),
                verticalArrangement = Arrangement.Center,
            ) {
                items(searchByArea!!) { item ->

                    Card(modifier = Modifier.padding(all = 20.dp)) {
                        Row(
                            modifier = Modifier.clickable {
                                navController.navigate(
                                    StartUpScreen.DetailScreen.route.replace(
                                        "{$MEAL}",
                                        "${item?.idMeal?.toInt()}"
                                    )
                                )
                            }
                        ) {
                            AsyncImage(
                                model = item?.strMealThumb, contentDescription = "Image",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.size(180.dp)
                            )
                            Column {
                                Text(
                                    text = "Meal Name : ${item?.strMeal}",
                                    modifier = Modifier.padding(all = 10.dp)
                                )
                                Text(
                                    text = "Meal Id : ${item?.idMeal}",
                                    modifier = Modifier.padding(all = 10.dp)
                                )
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


            Column(
                modifier = Modifier
                    .verticalScroll(scrollState)
                    .fillMaxSize()
            ) {
                Text(
                    text = "You search for $searchResult",
                    modifier = Modifier.padding(start = 20.dp),
                    fontFamily = FontFamily.Serif
                )
                Spacer(modifier = Modifier.padding(10.dp))
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .height(1000.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(searchByMealCategory!!) { item ->
                        Card(modifier = Modifier
                            .padding(all = 20.dp)
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
                                    modifier = Modifier.size(180.dp)
                                )
                                Column {
                                    Text(
                                        text = "Meal Name : ${item?.strMeal}",
                                        modifier = Modifier.padding(all = 20.dp)
                                    )
                                    Text(
                                        text = "Meal Id : ${item?.idMeal}",
                                        modifier = Modifier.padding(all = 20.dp)
                                    )
                                }
                            }
                        }
                    }

                }
            }
        }
    } else if (searchBy.equals("FirstLetter", true)) {

        val searchByMealFirstLetter = viewModelForApi.searchMealByFirstLetter
        viewModelForApi.getSearchMealByFirstLetter(searchResult)

        val scrollState = rememberScrollState()

        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .fillMaxSize()
        ) {
            Text(
                text = "You search for $searchResult",
                modifier = Modifier.padding(start = 20.dp, top = 20.dp),
                fontFamily = FontFamily.Serif
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .height(1000.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(searchByMealFirstLetter!!) { item ->
                    Card(modifier = Modifier
                        .padding(all = 20.dp)
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
                                modifier = Modifier.size(180.dp)
                            )
                            Column {
                                Text(
                                    text = "Meal Name : ${item?.strMeal}",
                                    modifier = Modifier.padding(all = 20.dp)
                                )
                                Text(
                                    text = "Meal Id : ${item?.idMeal}",
                                    modifier = Modifier.padding(all = 20.dp)
                                )
                            }
                        }
                    }
                }

            }
        }
    } else if (searchBy.equals("MealName", true)) {
        viewModelForApi.searchMealByName(searchResult)
        val searchByMealName = viewModelForApi.searchMealByName

        val scrollState = rememberScrollState()


        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .fillMaxSize()
        ) {
            Text(
                text = "You search for $searchResult",
                modifier = Modifier.padding(start = 20.dp, top = 20.dp),
                fontFamily = FontFamily.Serif
            )
            Spacer(modifier = Modifier.padding(10.dp))
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .height(1000.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(searchByMealName!!) { item ->
                    Card(modifier = Modifier
                        .padding(all = 20.dp)
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
                                modifier = Modifier.size(180.dp)
                            )
                            Column {
                                Text(
                                    text = "Meal Name : ${item.strMeal}",
                                    modifier = Modifier.padding(all = 20.dp)
                                )
                                Text(
                                    text = "Meal Id : ${item.idMeal}",
                                    modifier = Modifier.padding(all = 20.dp)
                                )
                            }
                        }
                    }
                }

            }
        }

    }

}