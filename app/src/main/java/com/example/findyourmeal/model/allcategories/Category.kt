package com.example.findyourmeal.model.allcategories

/**
 * This model file is for the Category which is for List all category
 * @param idCategory
 * @param strCategory
 * @param strCategoryDescription
 * @param strCategoryThumb
 */
data class Category(
    val idCategory: String,
    val strCategory: String,
    val strCategoryDescription: String,
    val strCategoryThumb: String
)