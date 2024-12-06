package dev.sondre.day05

import dev.sondre.Part

fun <T> List<T>.middle(): T {
    val mid = size.floorDiv(2)
    return this[mid]
}

class Rule(val first: Int, val second: Int) {
    companion object {
        fun fromString(s: String): Rule {
            val parts = s.split("|")
            return Rule(parts.first().toInt(), parts.last().toInt())
        }
    }

    fun toSet(): Set<Int> {
        return setOf(first, second)
    }
}

class Update(
    val pages: List<Int>
) {
    val relevantRules: MutableList<Rule> = mutableListOf()
    val pageNumberToIndex: Map<Int, Int> = pages.mapIndexed { index, page -> page to index }.toMap()

    companion object {
        fun fromString(s: String): Update {
            val parts = s.split(",")
            return Update(parts.toList().map { it.toInt() })
        }
    }

    fun toSet(): Set<Int> {
        return pages.toSet()
    }

    fun saveRelevantRules(rules: List<Rule>) {
        rules
            .filter { it.toSet().intersect(this.toSet()).size == 2 } // Both rules must be present in update
            .forEach { relevantRules.add(it) }
    }

    fun isValid(): Boolean {
        return relevantRules.all { rule -> pageNumberToIndex[rule.first]!! < pageNumberToIndex[rule.second]!! } // !! should be fine sine we are filtering out all irrelevant rules
    }
}

class Part1(expRes: Int? = null) : Part(expRes) {
    override fun solve(data: String): Int {
        val r = data.split("\n\n")
        val rules = r.first().lines().map { Rule.fromString(it) }
        val updates = r.last().lines().map { Update.fromString(it) }

        return updates
            .onEach { it.saveRelevantRules(rules) }
            .filter { it.isValid() }
            .sumOf { it.pages.middle() }
    }
}            