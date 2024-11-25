package dev.sondre.day01

import dev.sondre.Day
import dev.sondre.Env
import dev.sondre.PartEnum

fun main() {
    val day = Day(listOf(Part1(42), Part2(42)))
    day.all()
//    day.part(PartEnum.ONE, Env.TEST)
}
