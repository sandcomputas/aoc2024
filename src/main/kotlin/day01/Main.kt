package dev.sondre.day01

import dev.sondre.Day
import dev.sondre.PartEnum


fun main() {
    val day = Day(listOf(Part1(), Part2()))
    day.part(PartEnum.ALL)
}
