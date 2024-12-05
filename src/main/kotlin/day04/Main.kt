package dev.sondre.day04

import dev.sondre.Day
import dev.sondre.Env
import dev.sondre.PartEnum

fun main() {
    val day = Day(
        listOf(
            Part1(18),
//            Part2(42)
        )
    )
//    day.all()
    day.part(PartEnum.ONE, Env.TEST)
}