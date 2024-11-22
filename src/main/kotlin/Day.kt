package dev.sondre

enum class PartEnum(val i: Int) {
    ONE(1), TWO(2), ALL(3)
}

class Day(
    private val parts: List<Part>
) {
    fun all() {
        parts.forEach { runAndPrint(it) }
    }

    fun part(part: PartEnum) {
        val partsToRun = if (part == PartEnum.ALL) parts else parts.filter { it.part() == part.i}
        partsToRun.forEach { runAndPrint(it)}
    }

    private fun runAndPrint(part: Part) {
        part.test()
        part.actual()
        part.run()
        println(part)
    }
}
