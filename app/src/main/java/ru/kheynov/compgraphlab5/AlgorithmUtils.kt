package ru.kheynov.compgraphlab5

import androidx.compose.ui.geometry.Offset

fun isIntersectsRow(
    rowY: Double,
    rowLength: Double,
    line: Array<Array<Double>> //[(x1,y1), (x2, y2)]
): Boolean {
    val x1 = 0
    val x3 = line[0][0]
    val x4 = line[1][0]
    val y3 = line[0][1]
    val y4 = line[1][1]

    val t =
        ((x1 - x3) * (y3 - y4) - (rowY - y3) * (x3 - x4)) / ((x1 - rowLength) * (y3 - y4) - (rowY - rowY) * (x3 - x4))
    val u =
        ((x1 - x3) * (rowY - rowY) - (rowY - y3) * (x1 - rowLength)) / ((x1 - rowLength) * (y3 - y4) - (rowY - rowY) * (x3 - x4))
    return t in 0.0..1.0 && u in 0.0..1.0
}


fun getIntersectionPoints(
    points: Array<Offset>,
    start: Int,
    end: Int,
    step: Int,
    rowLength: Double,
    cellSize: Int
): Array<Offset> {
    val intersectionPoints = ArrayList<Offset>()
    val lines = mutableListOf<Array<Array<Double>>>()
    for (i in 0 until points.size - 1) {
        lines.add(
            arrayOf(
                arrayOf(
                    points[i].x.toDouble() * cellSize,
                    points[i].y.toDouble() * cellSize
                ),
                arrayOf(
                    points[i + 1].x.toDouble() * cellSize,
                    points[i + 1].y.toDouble() * cellSize
                )
            )
        )
    }
    lines.add(
        arrayOf(
            arrayOf(
                points.first().x.toDouble() * cellSize,
                points.first().y.toDouble() * cellSize
            ),
            arrayOf(
                points.last().x.toDouble() * cellSize,
                points.last().y.toDouble() * cellSize
            )
        )
    )

    for (row in (start until end step step)) {
        for (line in lines) {
            if (isIntersectsRow(row.toDouble(), rowLength, line)) {
                intersectionPoints.add(
                    getIntersectionPoint(
                        row.toDouble(),
                        rowLength,
                        line
                    )
                )
            }
        }
    }

    return intersectionPoints.toTypedArray()
}

fun getIntersectionPoint(
    rowY: Double,
    rowLength: Double,
    line: Array<Array<Double>>
): Offset {
    val x1 = 0
    val x3 = line[0][0]
    val x4 = line[1][0]
    val y3 = line[0][1]
    val y4 = line[1][1]
    val t =
        ((x1 - x3) * (y3 - y4) - (rowY - y3) * (x3 - x4)) / ((x1 - rowLength) * (y3 - y4) - (rowY - rowY) * (x3 - x4))
    val x = t * rowLength
    return Offset(x.toFloat(), rowY.toFloat())
}
