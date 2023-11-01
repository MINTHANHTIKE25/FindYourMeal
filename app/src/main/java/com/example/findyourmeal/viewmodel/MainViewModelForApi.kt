package com.example.findyourmeal.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.findyourmeal.data.repository.MyRepository
import com.example.findyourmeal.model.allcategories.Category
import kotlinx.coroutines.launch

class MainViewModelForApi(private val myRepository: MyRepository) : ViewModel() {
    var errorMessage : String by mutableStateOf("")
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
}