package dev.sondre.day05

import dev.sondre.Part

class Part2(expRes: Int? = null) : Part(expRes) {
    override fun solve(data: String): Int {
        val r = data.split("\n\n")
        val rules = r.first().lines().map { Rule.fromString(it) }
        val updates = r.last().lines().map { Update.fromString(it) }

        val inValidUpdates = updates
            .onEach { it.saveRelevantRules(rules) }
            .filterNot { it.isValid() }

        return inValidUpdates
            .map { it.sorted() }
            .sumOf { it.middle() }
    }
}            