package com.example.panstwamiastagame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.panstwamiastagame.dataclasses.Settings
import com.example.panstwamiastagame.screens.GameScreen
import com.example.panstwamiastagame.screens.MainScreen
import com.example.panstwamiastagame.screens.SplashScreen
import com.example.panstwamiastagame.ui.theme.PanstwaMiastaGameTheme

enum class Routes() {
    SplashScreen,
    MainScreen,
    GameScreen
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PanstwaMiastaGameTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                )
                { innerPadding ->
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = Routes.SplashScreen.name,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable(route = Routes.SplashScreen.name) {
                            SplashScreen(
                                navController = navController
                            )
                        }
                        composable(route = Routes.MainScreen.name) {
                            MainScreen(
                                navController = navController
                            )
                        }
                        composable(
                            route = Routes.GameScreen.name + "/{rounds}/{time}",
                            arguments = listOf(
                                navArgument(name = "rounds") {
                                    type = NavType.IntType
                                },
                                navArgument(name = "time") {
                                    type = NavType.IntType
                                }
                            )
                        ) { backStackEntry ->
                            GameScreen(
                                navController = navController,
                                settings = Settings(
                                    rounds = backStackEntry.arguments?.getInt("rounds"),
                                    time = backStackEntry.arguments?.getInt("time")
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}