package com.example.findyourmeal.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface SavedDataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(savedData: SavedData)

    @Delete
    suspend fun deleteSavedData(savedData: SavedData)
    @Query("Delete from saved_data_tb where mealId = :meal_Id")
    suspend fun deleteSavedWithMealId(meal_Id:String)
    @Query("Select * from saved_data_tb")
    fun getSavedData(): Flow<List<SavedData>>

    @Query("Delete from saved_data_tb")
    suspend fun deleteAllSavedData()
}