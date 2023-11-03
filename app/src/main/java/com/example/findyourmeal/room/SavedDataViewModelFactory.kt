package com.example.findyourmeal.room

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class SavedDataViewModelFactory(private val repo: SavedDataRepo) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = SavedDataViewModel(repo) as T
}