package com.example.findyourmeal.startup.mainlayout.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.findyourmeal.viewmodel.MainViewModelForApi

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBySearchView(
    chipName: String,
    mealId: Int,
    viewModelForApi: MainViewModelForApi,
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        var selected by remember {
            mutableStateOf(true)
        }
        var list = viewModelForApi.searchMealById
        FilterChip(selected = selected, onClick = {
            if (selected) {
                viewModelForApi.getSearchMealById(mealId.toString())
            } else {
                null
            }
        }, label = {
            Text(text = chipName)
        }, leadingIcon = {
            if (selected) {
                Icon(imageVector = Icons.Filled.Done, contentDescription = "Done")
            } else {
                null
            }
        })
    }
}