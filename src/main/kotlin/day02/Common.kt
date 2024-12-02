package dev.sondre.day02

import kotlin.math.abs

class Report(private val levels: List<Int>) {

    companion object {
        fun fromString(s: String): Report {
            return Report(
                s.split(" ")
                    .map { it.toInt() }
                    .toList()
            )
        }
    }

    fun safe(): Boolean {
        return (allIncreasing() || allDescending()) && adjacentLevelDiffInRange()
    }

    fun safeResilient(): Boolean {
        var s = safe()
        var counter = 0
        for (l in levels) {
            s = Report(levels.filterIndexed { i, _ -> i != counter }).safe()
            if (s) break
            counter++
        }
        return s
    }

    private fun allIncreasing(): Boolean {
        return inOrder {curr, prev -> curr > prev }
    }

    private fun allDescending(): Boolean {
        return inOrder {curr, prev -> curr < prev }
    }

    private fun inOrder(predicate: (current: Int, prev: Int) -> Boolean): Boolean {
        var prev: Int? = null
        for (current in levels) {
            if (prev == null) {
                prev = current
                continue
            }
            if (!predicate(current, prev)) return false
            prev = current
        }
        return true
    }

    private fun adjacentLevelDiffInRange(): Boolean {
        var prev: Int? = null
        for (current in levels) {
            if (prev == null) {
                prev = current
                continue
            }
            val diff = abs(current - prev)
            if (diff == 0 || diff > 3) return false
            prev = current
        }
        return true
    }
}