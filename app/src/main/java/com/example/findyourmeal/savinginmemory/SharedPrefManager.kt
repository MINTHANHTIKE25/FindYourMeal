package com.example.findyourmeal.savinginmemory

import android.content.Context
import android.content.SharedPreferences

class SharedPrefManager(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("MyPref", Context.MODE_PRIVATE)

    fun saveBoolean(key: String, value: Boolean) {
        val editor = sharedPreferences.edit()
        editor.putBoolean(key, value).apply()
    }

    fun saveLanguage(value: String?) {
        val editor = sharedPreferences.edit()
        editor.putString("language", value).apply()
    }

    fun retrieveBoolean(key: String, value: Boolean?): Boolean {
        return sharedPreferences.getBoolean(key, value!!)
    }

    fun retrieveLanguage(value: String?): String {
        return sharedPreferences.getString("language", value).toString()
    }

}