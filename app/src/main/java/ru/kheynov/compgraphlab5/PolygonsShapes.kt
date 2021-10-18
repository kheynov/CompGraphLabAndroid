package ru.kheynov.compgraphlab5

import androidx.compose.ui.geometry.Offset

enum class PolygonsShapes(val points: List<Offset>) {
    STAR(points = listOf(Offset(5f, 5f), Offset(1f, 1f), Offset(1f, 2f))),
    SQUARE(points = listOf(Offset(5f, 5f), Offset(10f, 5f), Offset(10f, 10f), Offset(5f, 10f))),
    TRIANGLE(points = listOf(Offset(0f, 0f), Offset(1f, 1f), Offset(1f, 2f)));
}