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

fun getIntersectionPoint(
    rowY: Double,
    rowLength: Double,
    line: Array<Array<Double>>
): Array<Double> {
    val x1 = 0
    val x3 = line[0][0]
    val x4 = line[1][0]
    val y3 = line[0][1]
    val y4 = line[1][1]
    val t =
        ((x1 - x3) * (y3 - y4) - (rowY - y3) * (x3 - x4)) / ((x1 - rowLength) * (y3 - y4) - (rowY - rowY) * (x3 - x4))
    val x = t * rowLength
    return arrayOf(x, rowY)
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
    var previousIndex: Int
    var nextIndex: Int
    for (line in lines) {
        for (row in (start until end step step)) {

            if (isIntersectsRow(row.toDouble(), rowLength, line)) {
                val intersectedPoint = getIntersectionPoint(
                    row.toDouble(),
                    rowLength,
                    line
                )
                intersectionPoints.add(
                    Offset(intersectedPoint[0].toFloat(), intersectedPoint[1].toFloat())
                )
            }
        }
    }

    for (index in points.indices) {
        if (index == 0) {
            previousIndex = points.size - 1
            nextIndex = index + 1
        } else if (index == points.size - 1) {
            nextIndex = 0
            previousIndex = index - 1
        } else {
            nextIndex = index + 1
            previousIndex = index - 1
        }
        if ((points[index].y * cellSize > points[previousIndex].y * cellSize && points[index].y * cellSize > points[nextIndex].y * cellSize) ||
            (points[index].y * cellSize < points[previousIndex].y * cellSize && points[index].y * cellSize < points[nextIndex].y * cellSize)
        ) {
            intersectionPoints.removeAll {
                it.x == points[index].x * cellSize &&
                        it.y == points[index].y * cellSize
            }
        }
    }

    return intersectionPoints.toTypedArray()
}

fun getRows(
    points: Array<Offset>
): Array<Float> {
    val rows = ArrayList<Float>()
    var row = 0f
    for (point in points) {
        if (point.y != row) {
            row = point.y
            if (!rows.contains(row)) {
                rows.add(row)
            }
        }
    }
    return rows.toTypedArray()
}
