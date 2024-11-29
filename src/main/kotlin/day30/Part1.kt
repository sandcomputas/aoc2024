package dev.sondre.day30

import dev.sondre.Part

class Part1(expRes: Int? = null) : Part(expRes) {
    override fun calc(data: String): Int {
        return data.lines().sumOf { it.toInt() }
    }
}
