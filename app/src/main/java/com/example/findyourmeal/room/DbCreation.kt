package com.example.findyourmeal.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [SavedData::class], version = 1, exportSchema = true)
abstract class DbCreation:RoomDatabase() {
    abstract fun getYourDao():SavedDataDao
}