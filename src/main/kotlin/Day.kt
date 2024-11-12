package dev.sondre

import java.io.FileNotFoundException

abstract class Day {

    fun data(env: String): String {
        val filePath = "/day1part1.txt"
        val content = object {}.javaClass.getResource(filePath)?.readText()
        return content ?: throw FileNotFoundException("Could not find file $filePath")
    }

    abstract fun calc(data: String): Int

    fun test(): Int {
        return calc(data("test"))
    }

    fun result(): Int {
        return calc(data("complete"))
    }
}