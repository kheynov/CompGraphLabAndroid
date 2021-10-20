package ru.kheynov.compgraphlab5

fun isIntersectsRow(
    rowY: Float,
    rowLength: Float,
    line: Array<Array<Float>> //[(x1,y1), (x2, y2)]
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
    rowY: Float,
    rowLength: Float,
    line: Array<Array<Float>>
): Array<Float> {
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
    points: Array<Array<Float>>,
    start: Int,
    end: Int,
    step: Int,
    rowLength: Float,
    cellSize: Int
): Array<Array<Float>> {
    val intersectionPoints = ArrayList<Array<Float>>()
    val lines = mutableListOf<Array<Array<Float>>>()
    for (i in 0 until points.size - 1) {
        lines.add(
            arrayOf(
                arrayOf(
                    points[i][0] * cellSize,
                    points[i][1] * cellSize
                ),
                arrayOf(
                    points[i + 1][0] * cellSize,
                    points[i + 1][1] * cellSize
                )
            )
        )
    }
    lines.add(
        arrayOf(
            arrayOf(
                points.first()[0] * cellSize,
                points.first()[1] * cellSize
            ),
            arrayOf(
                points.last()[0] * cellSize,
                points.last()[1] * cellSize
            )
        )
    )
    var previousIndex: Int
    var nextIndex: Int
    for (line in lines) {
        for (row in (start until end step step)) {

            if (isIntersectsRow(row.toFloat(), rowLength, line)) {
                val intersectedPoint = getIntersectionPoint(
                    row.toFloat(),
                    rowLength,
                    line
                )
                intersectionPoints.add(arrayOf(intersectedPoint[0], intersectedPoint[1]))
            }
        }
    }

    for (index in points.indices) {
        when (index) {
            0 -> {
                previousIndex = points.size - 1
                nextIndex = index + 1
            }
            points.size - 1 -> {
                nextIndex = 0
                previousIndex = index - 1
            }
            else -> {
                nextIndex = index + 1
                previousIndex = index - 1
            }
        }
        if ((points[index][1] * cellSize > points[previousIndex][1] * cellSize && points[index][1] * cellSize > points[nextIndex][1] * cellSize) ||
            (points[index][1] * cellSize < points[previousIndex][1] * cellSize && points[index][1] * cellSize < points[nextIndex][1] * cellSize)
        ) {
            intersectionPoints.removeAll {
                it[0] == points[index][0] * cellSize &&
                        it[1] == points[index][1] * cellSize
            }
        }
    }

    return intersectionPoints.toTypedArray()
}

fun getRows(
    points: Array<Array<Float>>
): Array<Float> {
    val rows = ArrayList<Float>()
    var row = 0f
    for (point in points) {
        if (point[1] != row) {
            row = point[1]
            if (!rows.contains(row)) {
                rows.add(row)
            }
        }
    }
    return rows.toTypedArray()
}

fun findInArray(
    point: Array<Float>,
    points: Array<Array<Float>>
): Boolean {
    for (i in points) {
        if (point[0] == i[0] && point[1] == i[1]) return true
    }
    return false
}