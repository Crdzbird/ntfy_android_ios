package ints.devotion.myapplication.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ints.devotion.myapplication.ui.map.MapScreen
import ints.devotion.navigator.ComposeNavigator
import ints.devotion.navigator.Screen

@Composable
fun StartupNavigation(navigator: ComposeNavigator) {
    val navController = rememberNavController()
    LaunchedEffect(Unit) { navigator.handleNavigationCommands(navController) }
    NavHost(navController = navController, startDestination = Screen.MapScreen.route) {
        composable(Screen.MapScreen.route) { MapScreen() }
    }
}