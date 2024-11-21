package dev.sondre

import java.io.FileNotFoundException

// TODO: add timing metrics

data class Result(
    var test: Int = -1,
    var actual: Int = -1
) {
    override fun toString(): String {
        val t = if (test == -1) "Not calculated" else test
        val a = if (actual == -1) "Not calculated" else actual
        return """
            |--> Test: $t
            |--> Actual: $a
        """.trimMargin()
    }
}

enum class Env {
    TEST, ACTUAL
}

abstract class Day {
    private val result = Result()

    private fun data(env: Env): String {
        return readFile(filePath(env))
    }

    private fun filePath(env: Env): String {
        val day = day()
        val part = part()
        return when (env) {
            Env.TEST -> "/day${day}test.txt"
            Env.ACTUAL -> "/day${day}part$part.txt"
        }
    }

    private fun readFile(filePath: String): String {
        val content = object {}.javaClass.getResource(filePath)?.readText()
        return content ?: throw FileNotFoundException("Could not find file $filePath")
    }

    private fun className() = this::class.java.name

    private fun day(): Int {
        val day = className().split(".")[2]
        return if (day.length == 5) (day.substring(3, 4)).toInt()
        else (day[3].toString().toInt())
    }

    private fun part(): Int {
        val part = className().split(".")[3]
        return if (part.length == 6) (part.substring(4, 5).toInt())
        else (part[4].toString().toInt())
    }

    abstract fun calc(data: String): Int

    fun test() {
        result.test = calc(data(Env.TEST))
    }

    fun actual() {
        result.actual = calc(data(Env.ACTUAL))
    }

    override fun toString(): String {
        return """
            |##### Day ${day()} #####
            |$result 
            |#################
        """.trimMargin()
    }
}