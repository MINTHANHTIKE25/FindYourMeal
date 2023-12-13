package com.example.findyourmeal.room

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SavedDataRepo @Inject constructor(
    private val savedDataDao: SavedDataDao
) {
    val allSavedData: Flow<List<SavedData>> = savedDataDao.getSavedData()

    suspend fun inserting(savedData: SavedData) {
        savedDataDao.insertData(savedData)
    }

    suspend fun deletingEachSaved(savedData: SavedData) {
        savedDataDao.deleteSavedData(savedData)
    }

    suspend fun deletingAllSaved() {
        savedDataDao.deleteAllSavedData()
    }

    suspend fun deleteWithId(meal_Id: String) {
        savedDataDao.deleteSavedWithMealId(meal_Id)
    }
}