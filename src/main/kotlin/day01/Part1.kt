package dev.sondre.day01

import dev.sondre.Part

class Part1(expRes: Int? = null) : Part(expRes) {
    override fun calc(data: String): Int {
        val l = mutableListOf(1)
        for (i in 1..100_000) {
            l.add(i * 100)
        }
        l.forEach { println(it) }
        return 42
    }
}