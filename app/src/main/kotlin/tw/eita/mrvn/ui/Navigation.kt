package tw.eita.mrvn.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import tw.eita.mrvn.Screen
import tw.eita.mrvn.ui.craft.CraftScreen
import tw.eita.mrvn.ui.home.HomeScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.HomeScreen.route) {
        composable(route = Screen.HomeScreen.route) {
            HomeScreen(navController = navController)
        }
        composable(route = Screen.CraftScreen.route) {
            CraftScreen()
        }
    }
}