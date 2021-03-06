package ru.kheynov.compgraphlab5


enum class PolygonsShapes(val points: Array<Array<Float>>) {
    SQUARE(
        points = arrayOf(
            arrayOf(3f, 5f),
            arrayOf(8f, 5f),
            arrayOf(8f, 10f),
            arrayOf(3f, 10f)
        )
    ),
    REVERSED_K(
        points = arrayOf(
            arrayOf(4f, 4f),
            arrayOf(7f, 4f),
            arrayOf(8f, 8f),
            arrayOf(7f, 12f),
            arrayOf(5f, 10f),
            arrayOf(2f, 12f),
            arrayOf(5f, 7f),
            arrayOf(3f, 5f),
            arrayOf(6f, 6f)
        )
    ),
    TRIANGLE(
        points = arrayOf(
            arrayOf(4f, 2f),
            arrayOf(8f, 6f),
            arrayOf(5f, 10f)
        )
    ),
    SOME_POLYGON(
        points = arrayOf(
            arrayOf(2f, 8f),
            arrayOf(4.5f, 9f),
            arrayOf(6f, 6f),
            arrayOf(9f, 9f),
            arrayOf(5f, 13f),
            arrayOf(3f, 12f)
        )
    ),
}