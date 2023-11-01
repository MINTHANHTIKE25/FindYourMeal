package com.example.findyourmeal.startup.mainlayout.mainscreen


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.findyourmeal.room.SavedDataViewModelFactory
import com.example.findyourmeal.ui.theme.PurpleGrey40
import com.example.findyourmeal.viewmodel.MainViewModelForApi


//Main Screen Setup
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(viewModelForApi: MainViewModelForApi,factory: SavedDataViewModelFactory) {
    val navController: NavHostController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavBar(navController = navController)
        }
    ) { paddingvalues ->
        paddingvalues
        MainScnNavGraph(viewModelForApi = viewModelForApi, navController,factory)
    }
}

@Composable
fun BottomNavBar(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Row(
        modifier = Modifier
            .height(80.dp)
            .background(Color.White)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = CenterVertically
    ) {
        navItems.forEach { screen ->
            val selected =
                currentDestination?.hierarchy?.any { it.route == screen.route } == true

            val contentColor = if (selected) Color.White else Color.Black

            val background = if (selected) PurpleGrey40.copy(0.3f) else Color.Transparent

            Box(
                modifier = Modifier
                    .height(40.dp)
                    .clip(CircleShape)
                    .background(background)
                    .clickable {
                        navController.navigate(screen.route) {
                            popUpTo(navController.graph.findStartDestination().id)
                            launchSingleTop = true
                        }
                    }) {
                Row(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    verticalAlignment = CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        imageVector = if (selected) screen.selectedIcon else screen.unselectedIcon,
                        contentDescription = "bottom navi icon",
                        tint = contentColor,
                        modifier = Modifier
                            .align(CenterVertically)
                            .padding(10.dp)
                    )
                    AnimatedVisibility(visible = selected) {
                        Text(
                            text = screen.label,
                            color = contentColor,
                            modifier = Modifier
                                .align(CenterVertically)
                                .padding(10.dp)
                        )
                    }

                }
            }
        }
    }
}
