package dev.sondre.day01

import dev.sondre.Part

fun List<Int>.count(n: Int): Int {
    return this.count { it == n }
}

class Part2(expRes: Int? = null) : Part(expRes) {
    override fun solve(data: String): Int {
        val (l1, l2) = readData(data)
        return l1
            .sumOf {
                first -> l2.count(first) * first
            }
    }
}
