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

    fun part(part: PartEnum, env: Env? = null) {
        val partsToRun = if (part == PartEnum.ALL) parts else parts.filter { it.part() == part.i }
        partsToRun.forEach { runAndPrint(it, env) }
    }

    private fun runAndPrint(part: Part, env: Env? = null) {
        val envs: MutableList<() -> Unit> = mutableListOf()
        when (env) {
            Env.TEST -> envs.add(part::test)
            Env.ACTUAL -> envs.add(part::actual)
            null -> {
                envs.add(part::test)
                envs.add(part::actual)
            }
        }
        envs.forEach { it.invoke() }
        part.run()
        println(part)
    }
}
