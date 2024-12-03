package dev.sondre.day03

import dev.sondre.Part

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
    override fun solve(data: String): Int {
        val p = Parser(data)
        val expressions = p.parse()
        return expressions.sumOf { it.evaluate() }
    }
}            