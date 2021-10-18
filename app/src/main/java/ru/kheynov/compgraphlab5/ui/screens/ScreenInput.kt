package ru.kheynov.compgraphlab5.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import ru.kheynov.compgraphlab5.ui.theme.CompGraphLab5Theme

@Composable
fun ScreenInput(navController: NavHostController) {
    CompGraphLab5Theme {

        Column(
            modifier = Modifier
                .fillMaxHeight(0.5f)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                modifier = Modifier.fillMaxWidth(0.7f),
                onClick = { navController.navigate(ScreenRoutes.SCREEN_DRAW.name + "/0") }) {
                Text(text = "DRAW A STAR")
            }
            Button(
                modifier = Modifier.fillMaxWidth(0.7f),
                onClick = { navController.navigate(ScreenRoutes.SCREEN_DRAW.name + "/1") }) {
                Text(text = "DRAW A SQUARE")
            }
            Button(
                modifier = Modifier.fillMaxWidth(0.7f),
                onClick = { navController.navigate(ScreenRoutes.SCREEN_DRAW.name + "/2") }) {
                Text(text = "DRAW A TRIANGLE")
            }
        }

    }

}
