package com.example.findyourmeal.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class ThemeViewModel:ViewModel (){
    var isDarkThemeEnabled = mutableStateOf(false)
        private set

    fun setTheme(isDarkTheme: Boolean) {
        isDarkThemeEnabled.value = isDarkTheme
    }
}