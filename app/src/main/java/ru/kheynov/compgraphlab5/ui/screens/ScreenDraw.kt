package ru.kheynov.compgraphlab5.ui.screens

import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PointMode
import androidx.navigation.NavHostController
import ru.kheynov.compgraphlab5.PolygonsShapes
import ru.kheynov.compgraphlab5.getIntersectionPoints
import kotlin.math.roundToInt

@Composable
fun ScreenDraw(navController: NavHostController, id: Int) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val cellSize = 100//grid cell size

        val pointsToDraw = mutableListOf<Offset>()
        PolygonsShapes.values()[id].points.forEach {
            pointsToDraw.add(Offset(it.x * cellSize, it.y * cellSize))
        }//getting a points coordinates

        val animationTargetState = remember {//remembering a state of currently drawing line
            mutableStateOf(0)
        }
        val animationIntState = animateIntAsState(
            targetValue = animationTargetState.value,
            animationSpec = tween(durationMillis = 3000)
        )

        Canvas(modifier = Modifier
            .fillMaxWidth()
            .weight(15f)
            .clickable {
                // Change the target state to start the animation
                animationTargetState.value = pointsToDraw.size - 1
            },
            onDraw = {

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

                //drawing dots at the vertices of the polygon
                drawPoints(
                    points = pointsToDraw,
                    strokeWidth = 10f,
                    pointMode = PointMode.Points,
                    color = Color.White
                )

                drawPoints(
                    points = getIntersectionPoints(
                        PolygonsShapes.values()[id].points.toTypedArray(),
                        start = 0,
                        end = height.toInt(),
                        step = cellSize / 4,
                        width.toDouble(),
                        cellSize = cellSize
                    ).toList(),
                    strokeWidth = 10f,
                    pointMode = PointMode.Points,
                    color = Color.Green
                )

                //drawing a border for a polygon
                for (i in 0 until pointsToDraw.size - 1) {
                    drawLine(
                        Color.Yellow,
                        pointsToDraw[i],
                        pointsToDraw[i + 1]
                    )
                }
                drawLine(Color.Yellow, pointsToDraw.first(), pointsToDraw.last())

                //current drawing line for 'paint out' algorithm
                val currentLine = animationIntState.value

            })

        Button(//button that returns to main screen
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            Text("DRAWING A ${PolygonsShapes.values()[id].name}")
        }

    }
}
