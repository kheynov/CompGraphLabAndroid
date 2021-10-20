package ru.kheynov.compgraphlab5.ui.screens

import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
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
import ru.kheynov.compgraphlab5.getRows
import ru.kheynov.compgraphlab5.ui.theme.canvasBackground
import ru.kheynov.compgraphlab5.ui.theme.lineColor
import ru.kheynov.compgraphlab5.ui.theme.primaryColor
import kotlin.math.roundToInt

@Composable
fun ScreenDraw(navController: NavHostController, id: Int) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(canvasBackground),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
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
            animationSpec = tween(durationMillis = 6000)
        )

        Canvas(modifier = Modifier
            .fillMaxWidth()
            .weight(15f),
            onDraw = {

                val width = this.size.width
                val height = this.size.height

                //drawing a grid
                for (i in 0 until (width / cellSize).roundToInt()) {
                    drawLine(
                        lineColor,
                        Offset(i.toFloat() * cellSize, 0f),
                        Offset(i.toFloat() * cellSize, height)
                    )
                }
                for (i in 0 until (height / cellSize).roundToInt()) {
                    drawLine(
                        lineColor,
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

                val intersectedPoints = getIntersectionPoints(
                    PolygonsShapes.values()[id].points.toTypedArray(),
                    start = 0,
                    end = height.toInt(),
                    step = cellSize / 4,
                    width.toDouble(),
                    cellSize = cellSize
                ).toList()

                drawPoints(
                    points = intersectedPoints,
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
                val rows = getRows(points = intersectedPoints.toTypedArray())
                animationTargetState.value = rows.size
                rows.sortBy { it }
                //current drawing line for 'paint out' algorithm
                for (i in 0 until animationIntState.value) {

                    val rowPoints = mutableListOf<Offset>()
                    for (point in intersectedPoints) {
                        if (point.y == rows[i] && !rowPoints.contains(point)) {
                            rowPoints.add(point)
                        }
                    }
                    rowPoints.sortBy { it.x }

                    if (rowPoints.size == 2) {
                        drawLine(
                            Color.Cyan,
                            Offset(rowPoints[0].x, rowPoints[0].y),
                            Offset(rowPoints[1].x, rowPoints[1].y)
                        )
                    } else {
                        drawLine(
                            Color.Cyan,
                            Offset(rowPoints[0].x, rowPoints[0].y),
                            Offset(rowPoints[1].x, rowPoints[1].y)
                        )
                        drawLine(
                            Color.Cyan,
                            Offset(rowPoints[2].x, rowPoints[2].y),
                            Offset(rowPoints[3].x, rowPoints[3].y)
                        )
                    }
                }

            }
        )

        Button(
//button that returns to main screen
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            onClick = { navController.popBackStack() },
        ) {
            Text("BACK")
        }
    }
}
