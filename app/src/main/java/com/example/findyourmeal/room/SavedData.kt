package com.example.findyourmeal.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Saved_Data_Tb")
data class SavedData(
    @PrimaryKey(autoGenerate = true)
    var tbId:Int=0,
    var mealId:String,
    var title:String,
    var photosUrl:String
)
