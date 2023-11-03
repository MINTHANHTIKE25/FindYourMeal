package com.example.findyourmeal.room

import kotlinx.coroutines.flow.Flow

class SavedDataRepo(private val savedDataDao: SavedDataDao) {
    val allSavedData:Flow<List<SavedData>> = savedDataDao.getSavedData()

    suspend fun inserting(savedData: SavedData){
        savedDataDao.insertData(savedData)
    }

    suspend fun deletingEachSaved(savedData: SavedData){
        savedDataDao.deleteSavedData(savedData)
    }

    suspend fun deletingAllSaved(){
        savedDataDao.deleteAllSavedData()
    }
}