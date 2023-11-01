package com.example.findyourmeal.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [SavedData::class], version = 1, exportSchema = true)
abstract class DbCreation:RoomDatabase() {
    abstract val dao:SavedDataDao
    companion object{
        @Volatile
        private var INSTANCE:DbCreation?=null
        fun getDataBase(context: Context):DbCreation{
            synchronized(this){
                var instance= INSTANCE
                if (instance==null){
                    instance= Room.databaseBuilder(
                        context.applicationContext,
                        DbCreation::class.java,
                        "Saved_DataBase"
                    ).build()
                }
                return instance
            }
        }
    }
}