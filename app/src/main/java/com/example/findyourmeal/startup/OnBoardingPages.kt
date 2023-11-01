package com.example.findyourmeal.startup

import android.media.Image
import android.media.MediaDescription
import androidx.annotation.DrawableRes
import com.example.findyourmeal.R

sealed class OnBoardingPages(
    @DrawableRes
    val image: Int,
    val title:String,
    val description: String
){
    object First:OnBoardingPages(
        image = R.drawable.eat_img,
        title = "Food",
        description = "We love food ,right. This app is for the people who " +
                "loved food and " +
                "want to try other tasty meal in ur kitchen "
    )
    object Second:OnBoardingPages(
        image = R.drawable.food_img,
        title = "Prepare Meal",
        description = "We need to prepare our meal.This apps include the methods of" +
                "how to prepare our ingredients "
    )
    object Third:OnBoardingPages(
        image = R.drawable.food_pic,
        title = "Search Ur Meal",
        description = "Our apps include search options to find " +
                "your favourite other country's food"
    )
}
