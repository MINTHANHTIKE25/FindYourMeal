package com.example.findyourmeal.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.findyourmeal.data.repository.MyRepository
import com.example.findyourmeal.model.allcategories.Category
import com.example.findyourmeal.model.sarchmealbyid.Meal
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModelForApi(private val myRepository: MyRepository) : ViewModel() {

    var errorMessage: String by mutableStateOf("")
    var allCategories: List<Category> by mutableStateOf(listOf())

    fun getAllCategories() {
        viewModelScope.launch {
            try {
                allCategories = myRepository.getAllCategories().categories
            } catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }

    var searchMealById: List<Meal?>? by mutableStateOf(listOf())
    // var meal  = mutableStateListOf<>()

    fun getSearchMealById(search: String) {
        viewModelScope.launch {
            try {
                searchMealById = myRepository.getSearchMealById(search).meals

            } catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }

    var searchMealByName: List<com.example.findyourmeal.model.searchmealbyname.Meal>? by mutableStateOf(
        listOf()
    )

    fun searchMealByName(search: String) {
        viewModelScope.launch {
            try {
                delay(500)
                searchMealByName = myRepository.getSearchMealByName(search).meals
            } catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }

    var searchMealByAreaList: List<com.example.findyourmeal.model.searchmealbyarea.Meal?>? by mutableStateOf(
        listOf()
    )

    fun getSearchMealByArea(searchMealByArea: String) {
        viewModelScope.launch {
            try {
                searchMealByAreaList = myRepository.getSearchMealByArea(searchMealByArea).meals
            } catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }

    var allAreaName: List<com.example.findyourmeal.model.listofallarea.Meal?>? by mutableStateOf(
        listOf()
    )

    fun getAllAreaNames() {
        viewModelScope.launch {
            try {
                allAreaName = myRepository.getAllAreaNames("list").meals
            } catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }

    var searchMealByFirstLetter: List<com.example.findyourmeal.model.searchbyfirstletter.Meal?>? by mutableStateOf(
        listOf()
    )

    fun getSearchMealByFirstLetter(character: String) {
        viewModelScope.launch {
            try {
                searchMealByFirstLetter = myRepository.getSearchByFirstLetter(character).meals
            } catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }

    var filterByCategory: List<com.example.findyourmeal.model.searchbycategory.Meal?>? by mutableStateOf(
        listOf()
    )

    fun getMealByCategory(category: String) {
        viewModelScope.launch {
            try {
                filterByCategory = myRepository.getMealFilterByCategory(category).meals
            } catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }
}