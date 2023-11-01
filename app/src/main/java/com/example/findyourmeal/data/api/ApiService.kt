package com.example.findyourmeal.data.api

import com.example.findyourmeal.model.allcategories.ListAllCategories
import retrofit2.http.GET

interface ApiService {

    /**
     * This function will return list of all categories from api with base url "categories.php"
     */
    @GET(ApiConstants.LIST_ALL_CATEGORIES)
    suspend fun getAllCategories(): ListAllCategories


}