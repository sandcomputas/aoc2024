package dev.sondre.day06

import dev.sondre.Part

class Part1(expRes: Int? = null) : Part(expRes) {
    override fun solve(data: String): Int {
        val l = Lab(data)
        println(l)
        l.walk()
        return l.guardSteps
    }
}