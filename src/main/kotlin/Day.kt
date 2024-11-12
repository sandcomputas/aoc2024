package dev.sondre

import java.io.FileNotFoundException

abstract class Day {

    fun data(env: String): String {
        // TODO: path should be read based, ideally on the name of the class it is being run from
        val className = this::class.java.name
        val day = parseDay(className)
        val part = parsePart(className)
        val filePath = "/day1part1.txt"
        val content = object {}.javaClass.getResource(filePath)?.readText()
        return content ?: throw FileNotFoundException("Could not find file $filePath")
    }

    private fun parseDay(className: String): Int {
        val day = className.split(".")[2]
        return if (day.length == 5) (day.substring(3,4)).toInt()
        else (day[3].toString().toInt())
    }

    private fun parsePart(className: String): Int {
        val part = className.split(".")[3]
        return if (part.length == 6) (part.substring(4,5).toInt())
        else (part[4].toString().toInt())
    }

    abstract fun calc(data: String): Int

    fun test(): Int {
        return calc(data("test"))
    }

    fun result(): Int {
        return calc(data("complete"))
    }
}