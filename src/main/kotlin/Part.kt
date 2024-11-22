package dev.sondre

import java.io.FileNotFoundException

const val DEFAULT_RESULT = -1

class Runner(
    private val env: Env,
    private val performance: PerformanceMonitor,
    private val data: String,
    private val function: (d: String) -> Int,
    private var result: Int = DEFAULT_RESULT
) {
    fun run() {
        result = performance.measure(data, function)
    }

    fun metrics(): String {
        return "${env.name} run took ${performance.timeToRun()}"
    }

    override fun toString(): String {
        val r = if (result == DEFAULT_RESULT) "Not calculated" else result
        return """
            |--> ${env.name}: $r
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

abstract class Part {
    private val runners: MutableList<Runner> = mutableListOf()

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

    private fun day(): Int {
        val dayString = className().split(".")[2]
        val d = if (dayString[3] == '0') dayString[4] else dayString.substring(3, 5)
        return d.toString().toInt()
    }

    private fun part(): Int {
        val part = className().split(".")[3]
        return if (part.length == 6) (part.substring(4, 5).toInt())
        else (part[4].toString().toInt())
    }

    abstract fun calc(data: String): Int

    fun test() {
        val runner = Runner(Env.TEST, PerformanceMonitor(), data(Env.TEST), ::calc)
        runners.add(runner)
    }

    fun actual() {
        val runner = Runner(Env.ACTUAL, PerformanceMonitor(), data(Env.ACTUAL), ::calc)
        runners.add(runner)
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