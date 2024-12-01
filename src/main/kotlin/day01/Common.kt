package dev.sondre.day01

import kotlin.math.abs

fun Pair<Int, Int>.distance(): Int {
    return abs(this.first - this.second)
}

fun readData(d: String): Pair<MutableList<Int>, MutableList<Int>> {
    val l1 = mutableListOf<Int>()
    val l2 = mutableListOf<Int>()

    d.lines().forEach {
        val row = it.split("""\s+""".toRegex())
        l1.add(row.first().toInt())
        l2.add(row.last().toInt())
    }
    return Pair(l1, l2)
}