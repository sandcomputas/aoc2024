package dev.sondre.day02

import dev.sondre.Part

class Part1(expRes: Int? = null) : Part(expRes) {
    override fun solve(data: String): Int {
        return data.lines()
            .map { Report.fromString(it) }
            .filter { it.safe() }
            .size
    }
}
