package dev.sondre.day04

import dev.sondre.Part





class Part2(expRes: Int? = null) : Part(expRes) {
    override fun solve(data: String): Int {

        Grid(data).flat()
        return 42
    }
}            