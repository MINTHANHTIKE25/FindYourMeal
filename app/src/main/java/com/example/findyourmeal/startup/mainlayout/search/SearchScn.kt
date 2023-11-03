@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.findyourmeal.startup.mainlayout.search

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.findyourmeal.startup.SEARCH_BY
import com.example.findyourmeal.startup.SearchResult
import com.example.findyourmeal.startup.StartUpScreen
import com.example.findyourmeal.startup.TEXT
import com.example.findyourmeal.viewmodel.MainViewModelForApi
import java.util.Locale
import java.util.regex.Pattern

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScn(navController: NavController, viewModelForApi: MainViewModelForApi) {

    viewModelForApi.getAllAreaNames()
    var text by rememberSaveable {
        mutableStateOf("")
    }
    var active by remember {
        mutableStateOf(false)
    }
    Column {

        SearchBar(query = text, onQueryChange = { text = it }, onSearch = {
            if (text.isNullOrEmpty()) {
                active = false
            } else {
                if (Pattern.matches("[a-zA-Z]",text)){
                    navController.navigate(StartUpScreen.SearchResult.route.replace(
                        "{$TEXT}/{$SEARCH_BY}",
                        "$text/FirstLetter"
                    ))
                }else if (Pattern.matches("[a-zA-Z]{2,}",text)){
                    navController.navigate(StartUpScreen.SearchResult.route.replace(
                        "{$TEXT}/{$SEARCH_BY}",
                        "$text/MealName"
                    ))
                }
            }

        }, active = active, onActiveChange = { active },
            modifier = Modifier
                .padding(top = 10.dp, start = 30.dp, end = 30.dp),
            placeholder = { Text(text = "Search") }
        ) {

        }

        PagingForSearchScreen(navController,viewModelForApi)

    }
}