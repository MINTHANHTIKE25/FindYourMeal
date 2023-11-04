package com.example.findyourmeal.viewmodel


import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.findyourmeal.savinginmemory.SharedPrefManager
import java.util.Locale

class LocaleViewModel : ViewModel() {

    var currentLocale : String = ""
    fun setLocale(locale: Locale, context: Context,saved : String) {
        val resources = context.resources
        val configuration = resources.configuration
        configuration.setLocale(locale)
        resources.updateConfiguration(configuration, resources.displayMetrics)
        currentLocale = saved
    }
}