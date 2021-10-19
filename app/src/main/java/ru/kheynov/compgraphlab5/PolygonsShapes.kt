package ru.kheynov.compgraphlab5

import androidx.compose.ui.geometry.Offset

enum class PolygonsShapes(val points: List<Offset>) {
    SQUARE(points = listOf(
        Offset(3f, 5f),
        Offset(8f, 5f),
        Offset(8f, 10f),
        Offset(3f, 10f)
    )),
    TRIANGLE(points = listOf(
        Offset(4f, 2f),
        Offset(8f, 6f),
        Offset(5f, 10f)
    )),
    SOME_POLYGON(points = listOf(
        Offset(2f, 8f),
        Offset(4.5f, 9f),
        Offset(6f, 6f),
        Offset(9f, 9f),
        Offset(5f, 13f),
        Offset(3f, 12f)

    )),

}