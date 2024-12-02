package dev.sondre.day02

import kotlin.math.abs

fun List<Int>.decreasing(): Boolean {
    return this.inOrder { prev, curr -> prev < curr }
}

fun List<Int>.increasing(): Boolean {
    return this.inOrder { prev, curr -> prev > curr }
}

fun List<Int>.inOrder(predicate: (prev: Int, curr: Int) -> Boolean): Boolean {
    for ((index, item) in this.withIndex()) {
        if (index == 0 || index == this.size) continue
        if (predicate(this[index - 1], item)) return false
    }
    return true
}

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
        return levels.increasing()
    }

    private fun allDescending(): Boolean {
        return levels.decreasing()
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