package ru.kheynov.compgraphlab5

enum class PolygonsShapes(points: Array<Array<Int>>, id: Int) {
    STAR(id = 0, points = arrayOf(arrayOf(0, 0), arrayOf(1, 1), arrayOf(1, 2))),
    SQUARE(id = 1, points = arrayOf(arrayOf(0, 0), arrayOf(1, 1), arrayOf(1, 2))),
    TRIANGLE(id = 2, points = arrayOf(arrayOf(0, 0), arrayOf(1, 1), arrayOf(1, 2)))
}