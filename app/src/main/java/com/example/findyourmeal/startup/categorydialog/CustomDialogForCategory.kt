package com.example.findyourmeal.startup.categorydialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.compose.AsyncImage

@Composable
fun CustomDialogForCategory(
    categoryName: String,
    img: String,
    description: String,
    onDismissDialog: (Boolean) -> Unit
) {

    var active by remember{
        mutableStateOf(false)
    }

    if (active){
        onDismissDialog(false)
    }else{
        onDismissDialog(true)
    }
    Dialog(
        onDismissRequest = { active = !active },
        properties = DialogProperties(
            dismissOnClickOutside = false,
            usePlatformDefaultWidth = false,
            decorFitsSystemWindows = false
        )
    ) {

        val verticalScroll = rememberScrollState()
        Card(
            modifier = Modifier
                .wrapContentSize()
                .padding(all = 40.dp)
                .verticalScroll(verticalScroll)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.LightGray)
                    .clip(MaterialTheme.shapes.medium),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = categoryName, fontSize = 30.sp, fontFamily = FontFamily.Serif,
                    modifier = Modifier
                        .padding(top = 20.dp, bottom = 40.dp)
                )

                AsyncImage(
                    model = img, contentDescription = "Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(all = 20.dp)
                        .size(200.dp),
                )

                Text(
                    text = description,
                    style = MaterialTheme.typography.labelLarge,
                    fontStyle = FontStyle.Normal,
                    fontFamily = FontFamily.SansSerif,
                    modifier = Modifier.padding(horizontal = 20.dp)
                )

                Button(
                    onClick = { active= !active },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 30.dp)
                ) {
                    Text(text = "Close", fontFamily = FontFamily.Monospace)
                }

            }
        }


    }
}