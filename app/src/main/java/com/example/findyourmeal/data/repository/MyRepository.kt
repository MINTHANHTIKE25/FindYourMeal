package com.example.findyourmeal.data.repository

import com.example.findyourmeal.data.api.RetrofitInstance
import com.example.findyourmeal.model.allcategories.ListAllCategories
import com.example.findyourmeal.model.sarchmealbyid.SearchMealById
import com.example.findyourmeal.model.searchmealbyname.SearchMealByName

class MyRepository {


    /**
     * Getting all categories list
     */
    suspend fun getAllCategories(): ListAllCategories {
        return RetrofitInstance.apiService.getAllCategories()
    }


    /**
     * Getting meal which is search by Id
     */
    suspend fun getSearchMealById(search: String): SearchMealById {
        return RetrofitInstance.apiService.getSearchMealById(search)
    }


    /**
     * Getting meal which is search by name
     */
    suspend fun getSearchMealByName(search: String): SearchMealByName {
        return RetrofitInstance.apiService.getSearchMealByName(search)
    }

}