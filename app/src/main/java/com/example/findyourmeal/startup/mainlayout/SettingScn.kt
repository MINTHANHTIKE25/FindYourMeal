package com.example.findyourmeal.startup.mainlayout

import android.app.Activity
import android.content.SharedPreferences
import android.content.Context
import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.findyourmeal.R
import com.example.findyourmeal.savinginmemory.SharedPrefManager
import com.example.findyourmeal.viewmodel.LocaleViewModel
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingScn(sharedPrefManager: SharedPrefManager,localeViewModel : LocaleViewModel,navController: NavController) {
    
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text =stringResource(id = R.string.settings),
                style = MaterialTheme.typography.headlineMedium)},
                modifier = Modifier.padding(bottom = 20.dp, start = 20.dp))
        }
    ) {paddingValues ->
        Spacer(modifier = Modifier.padding(vertical = 20.dp))
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = stringResource(id = R.string.select_language),
                fontFamily = FontFamily.Serif,
                fontSize = 25.sp,
                modifier = Modifier.padding(start = 20.dp)
            )
            Spacer(
                modifier = Modifier
                    .padding(vertical = 10.dp)
            )

            val context = LocalContext.current
            Text(
                text = "English",
                modifier = Modifier
                    .clickable {
                        sharedPrefManager.saveLanguage("en")
                        val locale = Locale("en")
                        localeViewModel.setLocale(locale, context, "en")
                        navController.popBackStack()

                    }
                    .fillMaxWidth()
                    .padding(all = 20.dp)
            )
            Spacer(
                modifier = Modifier
                    .padding(vertical = 0.6.dp)
                    .background(Color.Black)
            )

            Text(text = "မြန်မာ",
                modifier = Modifier
                    .clickable {
                        sharedPrefManager.saveLanguage("my")
                        val locale = Locale("my")
                        localeViewModel.setLocale(locale, context, "my")
                        navController.popBackStack()
                    }
                    .fillMaxWidth()
                    .padding(all = 20.dp)
            )

        }

    }

          
}
