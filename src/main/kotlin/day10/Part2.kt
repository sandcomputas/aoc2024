package dev.sondre.day10

import dev.sondre.Part

class Part2(expRes: Int? = null) : Part(expRes) {
    override fun solve(data: String): Int {
        val grid = Grid(data)
        return grid.allStarts.sumOf { it.walk() }
    }
}            