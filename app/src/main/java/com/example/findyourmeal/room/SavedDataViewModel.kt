package com.example.findyourmeal.room

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SavedDataViewModel @Inject constructor(
    private val repo: SavedDataRepo
) : ViewModel() {
    val allSavedData: Flow<List<SavedData>> = repo.allSavedData

    fun insertData(savedData: SavedData) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.inserting(savedData)
        }
    }

    fun deleteSavedData(savedData: SavedData) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.deletingEachSaved(savedData)
        }
    }

    fun deleteAll() {
        viewModelScope.launch(Dispatchers.IO) {
            repo.deletingAllSaved()
        }
    }

    fun deleteWithId(mealID: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.deleteWithId(mealID)
        }
    }
}