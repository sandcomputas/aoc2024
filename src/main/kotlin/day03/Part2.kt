package dev.sondre.day03

import dev.sondre.Part

enum class ExprType {
    MULT, DONT, DO, INCOMPLETE
}

class ConditionalParser(val program: String) {

    private val expressionMatchers = mapOf(
        ExprType.MULT to """.*mul\(\d+,\d+\).*""".toRegex(),
        ExprType.DONT to """.*don't\(\).*""".toRegex(),
        ExprType.DO to """.*do\(\).*""".toRegex()
    )

    fun parse(): List<Expression> {
        val exprBuffer = mutableListOf<Char>()
        val expressions = mutableListOf<Expression>()
        var currentStatus = true
        for (c in program) {
            when (expressionMatch(exprBuffer)) {
                ExprType.MULT -> {
                    val e = Expression(exprBuffer.joinToString(""))
                    if (!currentStatus) e.deactivate()
                    expressions.add(e)
                    exprBuffer.clear()
                }

                ExprType.DONT -> {
                    currentStatus = false
                    exprBuffer.clear()
                }

                ExprType.DO -> {
                    currentStatus = true
                    exprBuffer.clear()
                }

                ExprType.INCOMPLETE -> {}
            }
            exprBuffer.add(c)
        }
        return expressions
    }

    private fun expressionMatch(expr: List<Char>): ExprType {
        for (e in expressionMatchers) {
            if (e.value.containsMatchIn(expr.joinToString(""))) {
                return e.key
            }
        }
        return ExprType.INCOMPLETE
    }
}


class Part2(expRes: Int? = null) : Part(expRes) {
    override fun calc(data: String): Int {
        val p = ConditionalParser(data)
        val e = p.parse()
        return e.sumOf { it.evaluate() }
    }
}


fun main() {
    val expr = """.*mul\(\d+,\d+\).*""".toRegex()

    val p = "xmul(2,4)"

    val r = expr.containsMatchIn(p)
    println(r)
}