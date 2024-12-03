package dev.sondre.day03

import dev.sondre.Part

class Expression(private val raw: String) {

    fun evaluate(): Int {
        val expr = """mul\((?<p1>\d+),(?<p2>\d+)\)""".toRegex()
        val match = expr.find(raw) ?: throw IllegalStateException("No match found for digits in expression")
        val p1 = match.groups["p1"]?.value?.toInt() ?: throw IllegalStateException("No value for digit 1")
        val p2 = match.groups["p2"]?.value?.toInt() ?: throw IllegalStateException("No value for digit 2")
        return p1 * p2
    }
}

class Parser(private val program: String) {
    private val expr = """mul\(\d+,\d+\)""".toRegex()
    fun parse(): List<Expression> {
        return expr
            .findAll(program)
            .toList()
            .map { Expression(it.value) }
    }
}

class Part1(expRes: Int? = null) : Part(expRes) {
    override fun calc(data: String): Int {
        val p = Parser(data)
        val expressions = p.parse()
        return expressions.sumOf { it.evaluate() }
    }
}            