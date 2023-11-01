package com.example.findyourmeal.data.repository

import com.example.findyourmeal.data.api.RetrofitInstance
import com.example.findyourmeal.model.allcategories.ListAllCategories

class MyRepository {

    suspend fun getAllCategories(): ListAllCategories {
        return RetrofitInstance.apiService.getAllCategories()
    }
}