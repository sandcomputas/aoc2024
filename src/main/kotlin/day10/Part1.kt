package dev.sondre.day10

import dev.sondre.Part

class Part1(expRes: Int? = null) : Part(expRes) {
    override fun solve(data: String): Int {
        val grid = Grid(data)
        return grid.allStarts.map { it.walkUnique() }.map { it.distinct() }.map { it.size }.sum()
    }
}
