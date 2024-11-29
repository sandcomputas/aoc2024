package dev.sondre.day30

import dev.sondre.Day
// 2018 day 1
fun main() {
    val day = Day(
        listOf(
            Part1(4)
                .test("1\n2", 2)
                .test("1\n2", 20),
            Part2(10)
        )
    )
    day.all()
}    