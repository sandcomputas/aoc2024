package dev.sondre

import java.io.FileNotFoundException

const val DEFAULT_RESULT = -1

class Runner(
    private val env: Env,
    private val performance: PerformanceMonitor,
    private val data: String,
    private val function: (d: String) -> Int,
    private val expected: Int? = null
) {
    private var result: Int = DEFAULT_RESULT

    fun run() {
        result = performance.measure(data, function)
    }

    private fun metrics(): Int {
        return performance.timeToRun()
    }

    private fun expectationTest(): String {
        if (expected == null || result == DEFAULT_RESULT) return ""
        val prefix = "| "
        return if (expected == result) "$prefix ✅test passing✅" else "$prefix ❗️test fails❗️"
    }

    override fun toString(): String {
        val r = if (result == DEFAULT_RESULT) "Not calculated" else result
        val inputData = if (env == Env.TEST) "(input: ${data.replace("\n", ",")})" else ""
        val expectedResult = if (env == Env.TEST && expected != result) "(expected $expected)" else ""
        return """
            |--> ${env.name}: $r (${metrics()} ms) ${expectationTest()} $inputData $expectedResult
        """.trimMargin()
    }
}

class PerformanceMonitor {
    private var startTime: Long = 0
    private var endTime: Long = Long.MAX_VALUE

    fun measure(data: String, f: (data: String) -> Int): Int {
        startTime = System.currentTimeMillis()
        val r = f(data)
        endTime = System.currentTimeMillis()
        return r
    }

    fun timeToRun(): Int {
        return (endTime - startTime).toInt()
    }
}

enum class Env {
    TEST, ACTUAL
}

abstract class Part(private val expectedTestResult: Int? = null) {
    private val runners: MutableList<Runner> = mutableListOf()
    private var actualResult: Int? = null

    private fun data(env: Env): String {
        return readFile(filePath(env))
    }

    private fun filePath(env: Env): String {
        val day = day()
        return when (env) {
            Env.TEST -> "/day${day}test.txt"
            Env.ACTUAL -> "/day${day}.txt"
        }
    }

    private fun readFile(filePath: String): String {
        val content = object {}.javaClass.getResource(filePath)?.readText()
        return content ?: throw FileNotFoundException("Could not find file $filePath")
    }

    private fun className() = this::class.java.name

    private fun day(): String {
        val dayString = className().split(".")[2]
        return dayString.substring(3, 5)
    }

    private fun hasExpectedResult(): Boolean {
        return expectedTestResult != null
    }

    fun part(): Int {
        val part = className().split(".")[3]
        return if (part.length == 6) (part.substring(4, 5).toInt())
        else (part[4].toString().toInt())
    }

    abstract fun calc(data: String): Int

    fun test() {
        if (hasExpectedResult()) {
            runners.add(Runner(Env.TEST, PerformanceMonitor(), data(Env.TEST), ::calc, expectedTestResult))
        }
    }

    fun test(inputData: String, expectedResult: Int): Part {
        val inpData = inputData.replace(", ", "\n")
        runners.add(Runner(Env.TEST, PerformanceMonitor(), inpData, ::calc, expectedResult))
        return this
    }

    fun withActual(expected: Int): Part {
        actualResult = expected
        return this
    }

    fun actual() {
        runners.add(Runner(Env.ACTUAL, PerformanceMonitor(), data(Env.ACTUAL), ::calc, actualResult))
    }

    fun run() {
        runners.forEach { it.run() }
    }

    override fun toString(): String {
        val results = runners.joinToString("\n") { it.toString() }
        return """
            |####### Day ${day()} (part ${part()}) #######
            |$results
            |##############################
        """.trimMargin()
    }
}