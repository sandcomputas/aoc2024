package dev.sondre.day03

import dev.sondre.Day

fun main() {
    val day = Day(
        listOf(
            Part1()
                .test("xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))", 161)
                .withActual(174561379),
            Part2()
                .test("xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))", 48)
                .withActual(106921067)
        )
    )
    day.all()
}
