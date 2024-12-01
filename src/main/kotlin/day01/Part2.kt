package dev.sondre.day01

import dev.sondre.Part

class Part2(expRes: Int? = null) : Part(expRes) {
    override fun calc(data: String): Int {
        val (l1, l2) = readData(data)

        return l1
            .sumOf {
                first -> l2.count { second -> first == second } * first
            }

    }
}            