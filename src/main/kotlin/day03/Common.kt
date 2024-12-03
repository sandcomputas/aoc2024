package dev.sondre.day03

class Expression(private val raw: String) {
    private var active: Boolean = true

    fun evaluate(): Int {
        if (!active) return 0
        val expr = """.*mul\((?<p1>\d+),(?<p2>\d+)\).*""".toRegex()
        val match = expr.find(raw) ?: throw IllegalStateException("No match found for digits in expression")
        val p1 = match.groups["p1"]?.value?.toInt() ?: throw IllegalStateException("No value for digit 1")
        val p2 = match.groups["p2"]?.value?.toInt() ?: throw IllegalStateException("No value for digit 2")
        return p1 * p2
    }

   fun deactivate() {
       active = false
   }
}