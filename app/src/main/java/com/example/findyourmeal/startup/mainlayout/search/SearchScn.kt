@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.findyourmeal.startup.mainlayout.search

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.findyourmeal.viewmodel.MainViewModelForApi
import java.util.Locale
import java.util.regex.Pattern

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScn(navController: NavController, viewModelForApi: MainViewModelForApi) {


    var scrollState = rememberScrollState()
    var countyList = viewModelForApi.allAreaName
    viewModelForApi.getAllAreaNames()
    var text by remember {
        mutableStateOf("")
    }
    var active by remember {
        mutableStateOf(false)
    }

    var selected by remember {
        mutableStateOf(false)
    }
    Scaffold(topBar = {
        SearchBar(query = text, onQueryChange = { text = it }, onSearch = {

            if (text.isEmpty()) {
                active = false
            } else {
                if (text.length == 1 &&
                    Pattern.matches("[a-z]", text.lowercase(Locale.ROOT))
                ) {
                    viewModelForApi.getSearchMealById(text).toString()
                    Log.d("search", viewModelForApi.searchMealByFirstLetter.toString())

                }
                if
                        (Pattern.matches("[a-zA-Z]{2,}", text)) {
                    viewModelForApi.searchMealByName(text)
                    Log.d("search", viewModelForApi.searchMealByName.toString())
                }
                if
                        (Pattern.matches("[0 9]{2,}", text)) {
                    viewModelForApi.getSearchMealByArea(text)
                    Log.d("search", viewModelForApi.searchMealById.toString())
                }

            }

        }, active = active, onActiveChange = { active },
            modifier = Modifier
                .padding(vertical = 10.dp, horizontal = 30.dp)
                .verticalScroll(scrollState),
            placeholder = { Text(text = "Search") }
        ) {

        }
    }) { paddingValues ->
        paddingValues
    }
}