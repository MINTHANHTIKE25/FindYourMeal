package com.example.findyourmeal.data.repository

import com.example.findyourmeal.data.api.RetrofitInstance
import com.example.findyourmeal.model.allcategories.ListAllCategories
import com.example.findyourmeal.model.listofallarea.ListOfArea
import com.example.findyourmeal.model.sarchmealbyid.SearchMealById
import com.example.findyourmeal.model.searchbycategory.SearchByCategory
import com.example.findyourmeal.model.searchbyfirstletter.SearchByFirstLetter
import com.example.findyourmeal.model.searchmealbyarea.SearchMealByArea
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


    /**
     * Getting meal which is search by Area
     */
    suspend fun getSearchMealByArea(searchArea: String): SearchMealByArea {
        return RetrofitInstance.apiService.getSearchMealByArea(searchArea)
    }

    /**
     * Filter to get all area names
     */
    suspend fun getAllAreaNames(list: String): ListOfArea {
        return RetrofitInstance.apiService.getAllAreaName(list)
    }

    /**
     * Searching with firstLetter
     */
    suspend fun getSearchByFirstLetter(char: String): SearchByFirstLetter {
        return RetrofitInstance.apiService.getSearchMealByFirstLetter(char)
    }
    /**
     * Filter by category
     */
    suspend fun getMealFilterByCategory(category:String ) : SearchByCategory{
        return RetrofitInstance.apiService.getMealFilterByCategory(category)
    }
}