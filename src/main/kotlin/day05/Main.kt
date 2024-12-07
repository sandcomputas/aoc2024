package dev.sondre.day05

import dev.sondre.Day

fun main() {
    val day = Day(
        listOf(
            Part1(143)
                .withActual(7365),
            Part2(123)
                .withActual(5770)
        )
    )
    day.all()
}    