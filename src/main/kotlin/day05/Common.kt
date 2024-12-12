package dev.sondre.day05

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

class Update(val pages: List<Int>) {
    private val relevantRules: MutableList<Rule> = mutableListOf()
    private val pageNumberToIndex: Map<Int, Int> = pages.mapIndexed { index, page -> page to index }.toMap()

    companion object {
        fun fromString(s: String): Update {
            val parts = s.split(",")
            return Update(parts.toList().map { it.toInt() })
        }
    }

    private fun toSet(): Set<Int> = pages.toSet()

    fun saveRelevantRules(rules: List<Rule>) {
        rules
            .filter { it.toSet().intersect(this.toSet()).size == 2 } // Both rules must be present in update
            .forEach { relevantRules.add(it) }
    }

    fun isValid(): Boolean {
        return relevantRules.all { rule -> pageNumberToIndex[rule.first]!! < pageNumberToIndex[rule.second]!! } // !! should be fine sine we are filtering out all irrelevant rules
    }

    fun sorted(): List<Int> {
        val comp = Comparator { f: Int, s: Int ->
            val rule = relevantRules.first { (f == it.first && s == it.second) || (s == it.first && f == it.second) }
            when (rule.first) {
                f -> 1
                s -> -1
                else -> throw Exception("Invalid comparison, rule not matching page")
            }
        }
        return pages.sortedWith(comp)
    }
}

