package com.example.findyourmeal.startup.mainlayout.detailscreen

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.Absolute.SpaceEvenly
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.Start
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.findyourmeal.R
import com.example.findyourmeal.model.sarchmealbyid.Meal
import com.example.findyourmeal.room.SavedDataViewModel
import com.example.findyourmeal.room.SavedDataViewModelFactory
import com.example.findyourmeal.viewmodel.MainViewModelForApi

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScn(
    search: Int,
    viewModelForApi: MainViewModelForApi,
    mainNavController: NavController,
    factory: SavedDataViewModelFactory,
    viewModelFromRoom: SavedDataViewModel = viewModel(factory = factory)
) {
    var meal: List<Meal?>?
    if (viewModelForApi.searchMealById.isNullOrEmpty()) {
        LaunchedEffect(Unit) {
            meal = viewModelForApi.searchMealById
            viewModelForApi.getSearchMealById(search.toString())
        }
    }
    else {

        meal = viewModelForApi.searchMealById!!.filterNotNull()

        Log.d("get",meal.toString())

        viewModelForApi.getSearchMealById(search.toString())


        val ingredients = mutableListOf<Any?>()

        ingredients.add(meal!![0]?.strIngredient1!!)
        ingredients.add(meal!![0]?.strIngredient2!!)
        ingredients.add(meal!![0]?.strIngredient3!!)
        ingredients.add(meal!![0]?.strIngredient4!!)
        ingredients.add(meal!![0]?.strIngredient5!!)
        ingredients.add(meal!![0]?.strIngredient6!!)
        ingredients.add(meal!![0]?.strIngredient7!!)
        ingredients.add(meal!![0]?.strIngredient8!!)
        ingredients.add(meal!![0]?.strIngredient9!!)
        ingredients.add(meal!![0]?.strIngredient10!!)
        ingredients.add(meal!![0]?.strIngredient11!!)
        ingredients.add(meal!![0]?.strIngredient12!!)
        ingredients.add(meal!![0]?.strIngredient13!!)
        ingredients.add(meal!![0]?.strIngredient14!!)
        ingredients.add(meal!![0]?.strIngredient15!!)
        checkNullIngredient("${meal!![0]?.strIngredient16}",ingredients)
        checkNullIngredient("${meal!![0]?.strIngredient17}",ingredients)
        checkNullIngredient("${meal!![0]?.strIngredient18}",ingredients)
        checkNullIngredient("${meal!![0]?.strIngredient19}",ingredients)
        checkNullIngredient("${meal!![0]?.strIngredient20}",ingredients)


        val measurements = mutableListOf<Any?>()

        measurements.add(meal!![0]?.strMeasure1!!)
        measurements.add(meal!![0]?.strMeasure2!!)
        measurements.add(meal!![0]?.strMeasure3!!)
        measurements.add(meal!![0]?.strMeasure4!!)
        measurements.add(meal!![0]?.strMeasure5!!)
        measurements.add(meal!![0]?.strMeasure6!!)
        measurements.add(meal!![0]?.strMeasure7!!)
        measurements.add(meal!![0]?.strMeasure8!!)
        measurements.add(meal!![0]?.strMeasure9!!)
        measurements.add(meal!![0]?.strMeasure10!!)
        measurements.add(meal!![0]?.strMeasure11!!)
        measurements.add(meal!![0]?.strMeasure12!!)
        measurements.add(meal!![0]?.strMeasure13!!)
        measurements.add(meal!![0]?.strMeasure14!!)
        measurements.add(meal!![0]?.strMeasure15!!)
        checkNullMeasurement("${meal!![0]?.strMeasure16}",measurements)
        checkNullMeasurement("${meal!![0]?.strMeasure17}",measurements)
        checkNullMeasurement("${meal!![0]?.strMeasure18}",measurements)
        checkNullMeasurement("${meal!![0]?.strMeasure19}",measurements)
        checkNullMeasurement("${meal!![0]?.strMeasure20}",measurements)


        val mealIngredientAndMeasurement = mutableListOf<IngredientsAndMeasurements>()

        for (i in 0..19) {
            mealIngredientAndMeasurement.add(
                IngredientsAndMeasurements(
                    ingredients[i]!!.toString(),
                    measurements[i]!!.toString()
                )
            )
        }

        val afterFilter =
            mealIngredientAndMeasurement.filter { (ingredient, measurement) -> ingredient.isNotEmpty() && measurement.isNotEmpty() }

        Scaffold(
            topBar = {
                TopAppBar(title = {
                    Text(
                        text = meal!![0]!!.strMeal!!,
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

                        var addToFav by remember{ mutableStateOf(false) }
                        IconButton(onClick = {
                            addToFav =!addToFav
                            if (addToFav){

                            }
                        }) {
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
                    model = meal!![0]!!.strMealThumb, contentDescription = "meal image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.clip(MaterialTheme.shapes.medium)
                )

                //Meal Category
                Text(
                    text = "Category : ${meal!![0]!!.strCategory}", fontFamily = FontFamily.Serif,
                    modifier = Modifier
                        .padding(all = 20.dp)
                        .align(Start)
                )

                //Meal Area
                Text(
                    text = "Area : ${meal!![0]!!.strArea}", fontFamily = FontFamily.Serif,
                    modifier = Modifier
                        .padding(all = 20.dp)
                        .align(Start)
                )

                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.padding(all = 10.dp)

                ) {
                    Text(
                        text = "Ingredients",
                        fontFamily = FontFamily.Serif,
                        modifier = Modifier.padding(start = 20.dp, top = 10.dp, bottom = 10.dp),
                        style = MaterialTheme.typography.headlineMedium,
                        textAlign = TextAlign.Start


                    )
                    Text(
                        text = "Measurements",
                        fontFamily = FontFamily.Serif,
                        modifier = Modifier.padding(start = 20.dp, top = 10.dp, bottom = 10.dp),
                        style = MaterialTheme.typography.headlineMedium
                    )
                }

                afterFilter.forEach { item ->

                    Card(

                        modifier = Modifier
                            .fillMaxSize()
                            .padding(all = 10.dp)
                            .align(CenterHorizontally)
                    ) {
                        Row(
                            horizontalArrangement = SpaceEvenly
                        ) {

                            Text(
                                text = item.ingredient,
                                fontFamily = FontFamily.Serif,
                                modifier = Modifier.weight(1f).padding(start = 20.dp),
                                textAlign = TextAlign.Start
                            )
                            Text(
                                text = item.measurement,
                                fontFamily = FontFamily.Serif,
                                modifier = Modifier.padding(horizontal = 50.dp),
                                textAlign = TextAlign.End
                            )
                        }
                    }
                }

                Instruction(meal!![0]?.strInstructions!!)

                //ForYoutube Layout
                ForYouTube(link = meal!![0]!!.strYoutube!!)
            }
        }
    }
}

fun checkNullMeasurement(string: String?, measurements: MutableList<Any?>) {
    if (string.equals("null",true)){
        measurements.add("")
    }else{
        measurements.add(string.toString())
    }
}
fun checkNullIngredient(string: Any?, ingredients: MutableList<Any?>) {
    if (string== null){
        ingredients.add("")
    }else{
        ingredients.add(string.toString())
    }
}


@Composable
fun Instruction(instruction: String) {
    Text(
        text = instruction, fontFamily = FontFamily.SansSerif, fontSize = 16.sp,
        modifier = Modifier.padding(all = 20.dp)
    )
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

        val content = LocalContext.current
        Text(
            text = link,
            textDecoration = TextDecoration.Underline,
            color = Color.Blue,
            modifier = Modifier
                .padding(start = 20.dp)
                .clickable {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
                    content.packageManager.getLaunchIntentForPackage("com.google.android.youtube")
                    content.startActivity(intent)
                }
        )
    }
}
