package com.example.findyourmeal.startup.mainlayout.search

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.pagerTabIndicatorOffset

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PagingForSearchScreen() {
    val tabItems = listOf("Area", "Meals")
    val pagerState = rememberPagerState(pageCount = { tabItems.size })

    TabRow(
        selectedTabIndex = pagerState.currentPage,
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(all = 8.dp),
        indicator = {

        }
    ) {

    }

    HorizontalPager(
        state = pagerState,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
    ) { page ->
        Text(text = tabItems[page], fontFamily = FontFamily.Cursive)
    }
}