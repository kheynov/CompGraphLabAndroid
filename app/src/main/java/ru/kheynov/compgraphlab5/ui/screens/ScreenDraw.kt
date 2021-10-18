package ru.kheynov.compgraphlab5.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import kotlin.math.roundToInt

@Composable
fun ScreenDraw(navController: NavHostController, id: Int) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Canvas(modifier = Modifier
            .fillMaxWidth()
            .weight(15f), onDraw = {

            val cellSize = 100
            val width = this.size.width
            val height = this.size.height


            //drawing a grid
            for (i in 0 until (width / cellSize).roundToInt()) {
                drawLine(
                    Color.DarkGray,
                    Offset(i.toFloat() * cellSize, 0f),
                    Offset(i.toFloat() * cellSize, height)
                )
            }
            for (i in 0 until (height / cellSize).roundToInt()) {
                drawLine(
                    Color.DarkGray,
                    Offset(0f, i.toFloat() * cellSize),
                    Offset(width, i.toFloat() * cellSize)
                )
            }

        })
        Button(
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            Text("DRAWING $id")
        }

    }
}
