package com.example.findyourmeal.startup

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.findyourmeal.R
import com.example.findyourmeal.model.searchmealbyname.Meal
import com.example.findyourmeal.viewmodel.MainViewModelForApi

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScn(
    search: Int,
    viewModelForApi: MainViewModelForApi,
    mainNavController: NavController
) {
    LaunchedEffect(Unit) {
        viewModelForApi.searchMealByName(search.toString())
    }
    val meal: List<Meal>? = viewModelForApi.searchMealByName

    val ingredients = mutableListOf<String>()
    ingredients.add(meal!![0]!!.strIngredient1!!)
    ingredients.add(meal[0]!!.strIngredient2!!)
    ingredients.add(meal[0]!!.strIngredient3!!)
    ingredients.add(meal[0]!!.strIngredient4!!)
    ingredients.add(meal[0]!!.strIngredient5!!)
    ingredients.add(meal[0]!!.strIngredient6!!)
    ingredients.add(meal[0]!!.strIngredient7!!)
    ingredients.add(meal[0]!!.strIngredient8!!)
    ingredients.add(meal[0]!!.strIngredient9!!)
    ingredients.add(meal[0]!!.strIngredient10!!)
    ingredients.add(meal[0]!!.strIngredient11!!)
    ingredients.add(meal[0]!!.strIngredient12!!)
    ingredients.add(meal[0]!!.strIngredient13!!)
    ingredients.add(meal[0]!!.strIngredient14!!)
    ingredients.add(meal[0]!!.strIngredient15!!)
    ingredients.add(meal[0]!!.strIngredient16!!.toString())
    ingredients.add(meal[0]!!.strIngredient17!!.toString())
    ingredients.add(meal[0]!!.strIngredient18!!.toString())
    ingredients.add(meal[0]!!.strIngredient19!!.toString())
    ingredients.add(meal[0]!!.strIngredient20!!.toString())


    val measurements = mutableListOf<String>()
    measurements.add(meal[0]!!.strMeasure1!!)
    measurements.add(meal[0]!!.strMeasure2!!)
    measurements.add(meal[0]!!.strMeasure3!!)
    measurements.add(meal[0]!!.strMeasure4!!)
    measurements.add(meal[0]!!.strMeasure5!!)
    measurements.add(meal[0]!!.strMeasure6!!)
    measurements.add(meal[0]!!.strMeasure7!!)
    measurements.add(meal[0]!!.strMeasure8!!)
    measurements.add(meal[0]!!.strMeasure9!!)
    measurements.add(meal[0]!!.strMeasure10!!)
    measurements.add(meal[0]!!.strMeasure11!!)
    measurements.add(meal[0]!!.strMeasure12!!)
    measurements.add(meal[0]!!.strMeasure13!!)
    measurements.add(meal[0]!!.strMeasure14!!)
    measurements.add(meal[0]!!.strMeasure15!!)
    measurements.add(meal[0]!!.strMeasure16!!.toString())
    measurements.add(meal[0]!!.strMeasure17!!.toString())
    measurements.add(meal[0]!!.strMeasure18!!.toString())
    measurements.add(meal[0]!!.strMeasure19!!.toString())
    measurements.add(meal[0]!!.strMeasure20!!.toString())
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(
                    text = meal[0]!!.strMeal!!,
                    modifier = Modifier.padding(start = 20.dp),
                    fontFamily = FontFamily.SansSerif
                )
            }, navigationIcon = {
                IconButton(onClick = { mainNavController.popBackStack() }) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back")
                }
            },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Filled.Favorite, contentDescription = "Favourite"
                        )
                    }
                }
            )

        }
    ) { paddingValues ->
        paddingValues
    }

    Column(modifier = Modifier.fillMaxSize()) {


        //For meal Image
        AsyncImage(
            model = meal[0]!!.strMealThumb, contentDescription = "meal image",
            modifier = Modifier
                .clip(MaterialTheme.shapes.medium)
                .padding(all = 20.dp)
        )

        //Meal Category
        Text(
            text = "Category : ${meal[0]!!.strCategory}", fontFamily = FontFamily.Serif,
            modifier = Modifier.padding(all = 20.dp)
        )

        //Meal Area
        Text(
            text = "Area : ${meal[0]!!.strArea}", fontFamily = FontFamily.Serif,
            modifier = Modifier.padding(all = 20.dp)
        )

        //List for ingredients and measurements

        Row {
            Column {
                Text(
                    text = "Ingredients", fontFamily = FontFamily.SansSerif,
                    modifier = Modifier.padding(all = 20.dp)
                )

                ingredients.forEach { ingredient ->
                    IngredientsList(
                        ingredient = ingredient,
                        index = ingredients.indexOf(ingredient)
                    )
                }
            }

            Column {
                Text(
                    text = "Measurements", fontFamily = FontFamily.SansSerif,
                    modifier = Modifier.padding(all = 20.dp)
                )

                measurements.forEach { measurement ->
                    MeasurementsList(
                        measurement = measurement,
                        index = measurements.indexOf(measurement)
                    )
                }
            }
        }

        //ForYoutube Layout
        ForYouTube(link = meal[0]!!.strYoutube!!)
    }

}


@Composable
fun ForYouTube(link: String) {
    Image(
        painter = painterResource(id = R.drawable.youtube_img),
        contentDescription = "youtube image",
        modifier = Modifier.padding(all = 20.dp),
        contentScale = ContentScale.Crop
    )
    Text(
        text = link,
        textDecoration = TextDecoration.Underline,
        color = Color.Blue,
        modifier = Modifier.padding(start = 20.dp)
    )
}

//For Ingredients List
@Composable
fun IngredientsList(ingredient: String, index: Int) {
    Text(
        text = "Ingredient${index + 1} : $ingredient", fontFamily = FontFamily.SansSerif,
        modifier = Modifier.padding(20.dp)
    )
}

//For Measurements List
@Composable
fun MeasurementsList(measurement: String, index: Int) {
    Text(
        text = "Measurement${index + 1} : $measurement", fontFamily = FontFamily.SansSerif,
        modifier = Modifier.padding(20.dp)
    )
}
