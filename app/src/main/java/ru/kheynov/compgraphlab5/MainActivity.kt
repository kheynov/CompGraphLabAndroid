package ru.kheynov.compgraphlab5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import ru.kheynov.compgraphlab5.ui.screens.ScreenDraw
import ru.kheynov.compgraphlab5.ui.screens.ScreenInput
import ru.kheynov.compgraphlab5.ui.screens.ScreenRoutes
import ru.kheynov.compgraphlab5.ui.theme.CompGraphLab5Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CompGraphLab5Theme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = ScreenRoutes.SCREEN_INPUT.name
                ) {
                    composable(ScreenRoutes.SCREEN_INPUT.name) { ScreenInput(navController = navController) }
                    composable(
                        ScreenRoutes.SCREEN_DRAW.name + "/{id}",
                        arguments = listOf(
                            navArgument("id") {
                                type = NavType.IntType
                            })
                    ) { backStackEntry ->
                        ScreenDraw(
                            navController = navController,
                            backStackEntry.arguments?.getInt("id")!!
                        )
                    }
                }
            }
        }
    }
}
