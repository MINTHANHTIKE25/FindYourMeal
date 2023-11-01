package com.example.findyourmeal.navigation

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.navigation.NavType
import com.example.findyourmeal.model.searchmealbyname.Meal
import com.google.gson.Gson

class CustomNaviType : NavType<Meal>(isNullableAllowed = true) {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun get(bundle: Bundle, key: String): Meal? {
        return bundle.getParcelable(key,Meal::class.java)
    }

    override fun parseValue(value: String): Meal {
        return Gson().fromJson(value,Meal::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: Meal) {
        bundle.putParcelable(key,value)
    }

}