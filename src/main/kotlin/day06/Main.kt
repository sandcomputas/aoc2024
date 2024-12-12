package dev.sondre.day06

import dev.sondre.Day
import dev.sondre.Env
import dev.sondre.PartEnum


fun main() {
    val day = Day(
        listOf(
            Part1(41)
                .withActual(4722),
            Part2(6)
        )
    )

    day.all()
//    day.part(PartEnum.TWO, Env.TEST)

}

// 2299 was too high
// 1763 too high
