package dev.sondre.day01

import dev.sondre.Part
import kotlin.math.abs

fun Pair<Int, Int>.distance(): Int {
    return abs(this.first - this.second)
}

class Part1(expRes: Int? = null) : Part(expRes) {
    override fun calc(data: String): Int {
        val (l1, l2) = readData(data)
        l1.sort()
        l2.sort()
        return (l1 zip l2).sumOf { it.distance() }
    }
}

