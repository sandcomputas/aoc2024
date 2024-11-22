package dev.sondre

class Day(
    private val parts: List<Part>
) {
    fun all() {
        parts.forEach {
            it.test()
            it.actual()
            it.run()
            println(it)
        }
    }
}
