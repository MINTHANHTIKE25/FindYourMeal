package com.example.findyourmeal.viewmodel

import androidx.lifecycle.ViewModel
import java.util.Locale

class LocaleViewModel:ViewModel() {
    var currentLocale : Locale = Locale.getDefault()
}