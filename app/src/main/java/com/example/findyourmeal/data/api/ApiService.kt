package com.example.findyourmeal.data.api

import com.example.findyourmeal.model.allcategories.ListAllCategories
import com.example.findyourmeal.model.sarchmealbyid.SearchMealById
import com.example.findyourmeal.model.searchmealbyname.SearchMealByName
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    /**
     * This function will return list of all categories from api with base url "categories.php"
     */
    @GET(ApiConstants.LIST_ALL_CATEGORIES)
    suspend fun getAllCategories(): ListAllCategories


    /**
     * Searching meal with meal id
     */
    @GET(ApiConstants.SEARCH_BY_MEAL_ID)
    suspend fun getSearchMealById(
        @Query("i")
        searchMealById: String
    ): SearchMealById


    /**
     * Searching meal with Meal name
     */
    @GET(ApiConstants.SEARCH_MEAL_BY_NAME)
    suspend fun getSearchMealByName(
        @Query("s")
        searchMealByName: String
    ): SearchMealByName
}