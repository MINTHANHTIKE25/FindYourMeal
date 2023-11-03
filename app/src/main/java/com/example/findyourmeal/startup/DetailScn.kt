package com.example.findyourmeal.startup

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Start
import androidx.compose.ui.Alignment.Companion.Top
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
import com.example.findyourmeal.model.sarchmealbyid.Meal
import com.example.findyourmeal.viewmodel.MainViewModelForApi

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScn(
    search: Int,
    viewModelForApi: MainViewModelForApi,
    mainNavController: NavController
) {

    val meal: List<Meal?>?
    if (viewModelForApi.searchMealById.isNullOrEmpty()) {
        LaunchedEffect(Unit) {
            viewModelForApi.getSearchMealById(search.toString())
        }
    } else {
        meal = viewModelForApi.searchMealById
        viewModelForApi.getSearchMealById(search.toString())

        val ingredients = mutableListOf<String>()
        ingredients.add(meal!![0]?.strIngredient1!!)
        ingredients.add(meal[0]?.strIngredient2!!)
        ingredients.add(meal[0]?.strIngredient3!!)
        ingredients.add(meal[0]?.strIngredient4!!)
        ingredients.add(meal[0]?.strIngredient5!!)
        ingredients.add(meal[0]?.strIngredient6!!)
        ingredients.add(meal[0]?.strIngredient7!!)
        ingredients.add(meal[0]?.strIngredient8!!)
        ingredients.add(meal[0]?.strIngredient9!!)
        ingredients.add(meal[0]?.strIngredient10!!)
        ingredients.add(meal[0]?.strIngredient11!!)
        ingredients.add(meal[0]?.strIngredient12!!)
        ingredients.add(meal[0]?.strIngredient13!!)
        ingredients.add(meal[0]?.strIngredient14!!)
        ingredients.add(meal[0]?.strIngredient15!!)
        ingredients.add(meal[0]?.strIngredient16!!.toString())
        ingredients.add(meal[0]?.strIngredient17!!.toString())
        ingredients.add(meal[0]?.strIngredient18!!.toString())
        ingredients.add(meal[0]?.strIngredient19!!.toString())
        ingredients.add(meal[0]?.strIngredient20!!.toString())


        val measurements = mutableListOf<String>()
        measurements.add(meal[0]?.strMeasure1!!)
        measurements.add(meal[0]?.strMeasure2!!)
        measurements.add(meal[0]?.strMeasure3!!)
        measurements.add(meal[0]?.strMeasure4!!)
        measurements.add(meal[0]?.strMeasure5!!)
        measurements.add(meal[0]?.strMeasure6!!)
        measurements.add(meal[0]?.strMeasure7!!)
        measurements.add(meal[0]?.strMeasure8!!)
        measurements.add(meal[0]?.strMeasure9!!)
        measurements.add(meal[0]?.strMeasure10!!)
        measurements.add(meal[0]?.strMeasure11!!)
        measurements.add(meal[0]?.strMeasure12!!)
        measurements.add(meal[0]?.strMeasure13!!)
        measurements.add(meal[0]?.strMeasure14!!)
        measurements.add(meal[0]?.strMeasure15!!)
        measurements.add(meal[0]?.strMeasure16!!.toString())
        measurements.add(meal[0]?.strMeasure17!!.toString())
        measurements.add(meal[0]?.strMeasure18!!.toString())
        measurements.add(meal[0]?.strMeasure19!!.toString())
        measurements.add(meal[0]?.strMeasure20!!.toString())


        val trueIngredients = ingredients.filter { it.isNotBlank() }

        val trueMeasure = measurements.filter { it.isNotBlank() }
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
                                imageVector = Icons.Filled.Favorite,
                                contentDescription = "Favourite"
                            )
                        }
                    }
                )

            }
        ) { paddingValues ->

            val scrollState = rememberScrollState()
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .verticalScroll(scrollState),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                //For meal Image
                AsyncImage(
                    model = meal[0]!!.strMealThumb, contentDescription = "meal image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.clip(MaterialTheme.shapes.medium)
                )

                //Meal Category
                Text(
                    text = "Category : ${meal[0]!!.strCategory}", fontFamily = FontFamily.Serif,
                    modifier = Modifier
                        .padding(all = 20.dp)
                        .align(Start)
                )

                //Meal Area
                Text(
                    text = "Area : ${meal[0]!!.strArea}", fontFamily = FontFamily.Serif,
                    modifier = Modifier
                        .padding(all = 20.dp)
                        .align(Start)
                )

                //List for ingredients and measurements

                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier
                            .padding(start = 10.dp)
                            .align(Top)
                    ) {
                        Text(
                            text = "Ingredients", fontFamily = FontFamily.SansSerif,
                            modifier = Modifier.padding(all = 20.dp)
                        )

                        trueIngredients.forEach { finalIngredients ->
                            IngredientsList(
                                ingredient = finalIngredients,
                                index = trueIngredients.indexOf(finalIngredients)
                            )
                        }

                    }

                    Spacer(modifier = Modifier.padding(horizontal = 20.dp))
                    Column(
                        modifier = Modifier
                            .padding(start = 10.dp)
                            .align(Top)
                    ) {
                        Text(
                            text = "Measurements", fontFamily = FontFamily.SansSerif,
                            modifier = Modifier.padding(all = 20.dp)
                        )

                        trueMeasure.forEach { final ->
                            MeasurementsList(
                                measurement = final,
                                index = trueMeasure.indexOf(final)
                            )
                        }
                    }
                }

                //ForYoutube Layout
                ForYouTube(link = meal[0]!!.strYoutube!!)
            }
        }


    }
}


@Composable
fun ForYouTube(link: String) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(all = 15.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.youtube_img),
            contentDescription = "youtube image",
            modifier = Modifier
                .padding(all = 20.dp)
                .size(60.dp),
            contentScale = ContentScale.Crop
        )
        Text(
            text = link,
            textDecoration = TextDecoration.Underline,
            color = Color.Blue,
            modifier = Modifier.padding(start = 20.dp)
        )
    }

}

//For Ingredients List
@Composable
fun IngredientsList(ingredient: String, index: Int) {
    Card(
        modifier = Modifier
            .background(Color.Transparent)
            .padding(all = 10.dp)
    ) {
        Text(
            text = "${index + 1} : $ingredient", fontFamily = FontFamily.SansSerif,
            modifier = Modifier.padding(all = 10.dp)
        )
    }

}

//For Measurements List
@Composable
fun MeasurementsList(measurement: String, index: Int) {
    Card(
        modifier = Modifier
            .background(Color.Transparent)
            .padding(all = 10.dp)
    ) {
        Text(
            text = "${index + 1} : $measurement", fontFamily = FontFamily.SansSerif,
            modifier = Modifier.padding(all = 10.dp)
        )
    }

}
