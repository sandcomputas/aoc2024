package dev.sondre

enum class PartEnum(val p: Int) {
    ONE(1), TWO(2), ALL(3)
}

class Day(
    private val parts: List<Part>
) {
    fun all() {
        parts.forEach { runAndPrint(it) }
    }

    fun part(part: PartEnum, env: Env? = null) {
        val partsToRun = if (part == PartEnum.ALL) parts else parts.filter { it.part() == part.p }
        partsToRun.forEach { runAndPrint(it, env) }
    }

    private fun runAndPrint(part: Part, env: Env? = null) {
        when (env) {
            Env.TEST -> part.test()
            Env.ACTUAL -> part.actual()
            null -> {
                part.test()
                part.actual()
            }
        }
        part.run()
        println(part)
    }
}
