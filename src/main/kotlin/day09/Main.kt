package dev.sondre.day09

import dev.sondre.Day

fun main() {
    val day = Day(
        listOf(
            Part1(1928)
                .test("12345", 60),
            Part2(42)
        )
    )
    day.all()
}

// 1454954746 too low