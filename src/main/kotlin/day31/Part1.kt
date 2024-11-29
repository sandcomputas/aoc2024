package dev.sondre.day31

import dev.sondre.Part

class Part1(expRes: Int? = null) : Part(expRes) {
    override fun calc(data: String): Int {
        var twoCount = 0
        var threeCount = 0
        data.lines()
            .forEach { line ->
                line.groupBy { char -> char }
                    .map { it.value.size }
                    .toSet() // To avoid counting aabcc as +2 twoCount
                    .forEach {
                        if (it == 2) twoCount++
                        if (it == 3) threeCount++
                    }
            }
        return twoCount * threeCount
    }
}
