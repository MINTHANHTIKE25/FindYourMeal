package com.example.findyourmeal.data.api

import com.example.findyourmeal.model.allcategories.ListAllCategories
import com.example.findyourmeal.model.listofallarea.ListOfArea
import com.example.findyourmeal.model.sarchmealbyid.SearchMealById
import com.example.findyourmeal.model.searchbycategory.SearchByCategory
import com.example.findyourmeal.model.searchbyfirstletter.SearchByFirstLetter
import com.example.findyourmeal.model.searchmealbyarea.SearchMealByArea
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

    /**
     * Getting meal with Area
     */
    @GET(ApiConstants.FILTER_BY)
    suspend fun getSearchMealByArea(
        @Query("a")
        searchArea: String
    ): SearchMealByArea

    /**
     * Getting list of all area names
     */
    @GET(ApiConstants.ALL_AREA_NAMES)
    suspend fun getAllAreaName(
        @Query("a")
        filterArea: String
    ): ListOfArea

    /**
     * Searching meal with first letter
     */
    @GET(ApiConstants.SEARCH_MEAL_BY_NAME)
    suspend fun getSearchMealByFirstLetter(
        @Query("f")
        searchMealByName: String
    ): SearchByFirstLetter

    /**
     * Filter by category
     */
    @GET(ApiConstants.FILTER_BY)
    suspend fun getMealFilterByCategory(
        @Query("c")
        category : String
    ) : SearchByCategory

}