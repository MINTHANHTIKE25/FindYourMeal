package com.example.findyourmeal.model.allcategories

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * This model file is for the Category which is for List all category
 * @param idCategory
 * @param strCategory
 * @param strCategoryDescription
 * @param strCategoryThumb
 */

@Parcelize
data class Category(
    val idCategory: String,
    val strCategory: String,
    val strCategoryDescription: String,
    val strCategoryThumb: String
): Parcelable