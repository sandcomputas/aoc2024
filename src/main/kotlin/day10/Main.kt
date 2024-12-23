package dev.sondre.day10

import dev.sondre.Day

fun main() {
    val day = Day(
        listOf(
            Part1(36)
                .test(
                    "...0...\n" +
                            "...1...\n" +
                            "...2...\n" +
                            "6543456\n" +
                            "7.....7\n" +
                            "8.....8\n" +
                            "9.....9", 2
                )
                .test(
                    "..90..9\n" +
                            "...1.98\n" +
                            "...2..7\n" +
                            "6543456\n" +
                            "765.987\n" +
                            "876....\n" +
                            "987....", 4
                )
                .test(
                    "10..9..\n" +
                            "2...8..\n" +
                            "3...7..\n" +
                            "4567654\n" +
                            "...8..3\n" +
                            "...9..2\n" +
                            ".....01", 3
                )
                .withActual(617),
            Part2(81)
                .withActual(1477)
        )
    )
    day.all()
}    