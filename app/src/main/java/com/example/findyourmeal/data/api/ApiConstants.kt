package com.example.findyourmeal.data.api

object ApiConstants {

    //Base Url for Meal db
    const val BASE_URL = "https://www.themealdb.com/api/json/v1/1/"

    //end point for list all categories
    const val LIST_ALL_CATEGORIES = "categories.php"

    //search meal by name/by first letter ?f=/
    const val SEARCH_MEAL_BY_NAME = "search.php"

    //search by meal id ?i=
    const val SEARCH_BY_MEAL_ID = "lookup.php"

    //search meal by area  ?a= /category ?c=
    const val FILTER_BY = "filter.php"

    //Filter all area name ?a=list
    const val ALL_AREA_NAMES = "list.php"
}