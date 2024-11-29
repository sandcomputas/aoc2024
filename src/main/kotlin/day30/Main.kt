package dev.sondre.day30

import dev.sondre.Day
// 2018 day 1
fun main() {
    val day = Day(
        listOf(
            Part1(4)
                .test("+1, +1, +1", 3)
                .test("+1, +1, -2", 0)
                .test("-1, -2, -3", -6),
            Part2(10)
                .test("+1, -1", 0)
                .test("+3, +3, +4, -2, -4", 10)
                .test("-6, +3, +8, +5, -6", 5)
                .test("+7, +7, -2, -7, -4", 14)
        )
    )
    day.all()
}    