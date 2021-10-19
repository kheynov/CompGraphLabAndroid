package ru.kheynov.compgraphlab5

import androidx.compose.ui.geometry.Offset

fun isIntersectsRow(
    rowY: Double,
    rowLength: Double,
    line: Array<Offset> //[(x1,y1), (x2, y2)]
): Boolean {
    val t =
        ((0 - line[0].x) * (line[0].y - line[1].y) - (rowY - line[0].y) * (line[0].x - line[1].x)) / ((0 - rowLength) * (line[0].y - line[1].y) - (rowY - rowY) * (line[0].x - line[1].x))
    val u =
        ((0 - line[0].x) * (rowY - rowY) - (rowY - line[0].y) * (0 - rowLength)) / ((0 - rowLength) * (line[0].y - line[1].y) - (rowY - rowY) * (line[0].x - line[1].x))
    return t in 0.0..1.0 && u in 0.0..1.0
}
