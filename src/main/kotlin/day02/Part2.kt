package dev.sondre.day02

import dev.sondre.Part

class Part2(expRes: Int? = null) : Part(expRes) {
    override fun calc(data: String): Int {
        return data.lines()
            .map { Report.fromString(it) }
            .filter { it.safeResilient() }
            .size
    }
}

// Too low 415
