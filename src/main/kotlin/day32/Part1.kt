package dev.sondre.day32

import dev.sondre.Part
import dev.sondre.productOf
import dev.sondre.skipFirst

fun readLine1(data: String): List<Long> {
    return data
        .split("""\s+""".toRegex())
        .toList()
        .skipFirst()
        .map { it.toLong() }
}

class Part1(expRes: Int? = null) : Part(expRes) {
    override fun calc(data: String): Int {
        val times = readLine1(data.lines().first())
        val distances = readLine1(data.lines().last())

        return (times zip distances)
            .map { Race.fromPair(it) }
            .map { race -> race.numberOfWaysToWin() }
            .productOf()
    }
}
