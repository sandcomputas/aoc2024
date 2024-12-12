package dev.sondre.day06

import java.util.*

class Guard {
    var current = 0
    val allDirs = listOf('^', '>', 'v', '<')
    fun changeDirection() {
        current = (current + 1) % 4
    }

    val direction: Char
        get() = allDirs[current]

    fun move(): Pair<Int, Int> {
        return when (direction) {
            '^' -> Pair(-1, 0)
            '>' -> Pair(0, +1)
            'v' -> Pair(1, 0)
            '<' -> Pair(0, -1)
            else -> throw Exception("Invalid direction")
        }
    }
}

class Lab(raw: String) {
    val map = raw.lines().map { it.toCharArray().toMutableList() }
    var pos: Pair<Int, Int> // row, col
    val startPos: Pair<Int, Int>
    var guardSteps = 1
    val guard = Guard()
    val visited: MutableList<Pair<Int, Int>> = mutableListOf()

    init {
        var stepsToFirst = 0
        val rawWithoutNewLine = raw.replace("\n", "")
        while (rawWithoutNewLine[stepsToFirst] != '^') {
            stepsToFirst++
        }
        stepsToFirst++
        val rowLength = raw.lines().first().length
        val row = stepsToFirst.floorDiv(rowLength)
        val column = stepsToFirst - (rowLength * row) - 1
        pos = Pair(row, column)
        startPos = pos.copy()
    }

    fun walk() {
        while (!visitedTwice()) {
            var move = guard.move()
            if (nextIsExit(move)) {
                break
            } else if (nextIsBlocked(move)) {
                guard.changeDirection()
                move = guard.move() // Need to update the move since the direction has changed
            }
            updatePos(move)
        }
    }

    fun hasCycle(): Boolean {
        return visitedTwice()
//        return visitedTwice2()
    }

    fun visitedTwice(): Boolean {
        val groups = visited.groupBy { it }.filterNot { it.key == startPos}
        if (groups.isEmpty()) return false
        return groups.filter { it.value.size > 5 }.size > 4 // all eller any?
    }

    private fun visitedTwice2(): Boolean {
        return visited.size > setOf(visited).size
    }

    private fun updatePos(move: Pair<Int, Int>) {
        if (map[pos.first][pos.second] != 'X') {
            map[pos.first][pos.second] = 'X'
        }
        pos = Pair(pos.first + move.first, pos.second + move.second)
        visited.add(pos)
//        println(pos)
        if (map[pos.first][pos.second] != 'X') guardSteps++
        map[pos.first][pos.second] = guard.direction
    }

    private fun nextIsBlocked(move: Pair<Int, Int>): Boolean {
        return map[pos.first + move.first][pos.second + move.second] == '#'
    }

    private fun nextIsExit(move: Pair<Int, Int>): Boolean {
        return (pos.first + move.first > map.size - 1)
                || (pos.first + move.first < 0)
                || (pos.second + move.second > map.first().size - 1)
                || (pos.second + move.second < 0)
    }

    fun addBlock(blockPos: Pair<Int, Int>) {
        map[blockPos.first][blockPos.second] = '#'
    }

    override fun toString(): String {
        return map.joinToString("\n") { it.joinToString("") }
    }
}

fun main() {
    val l = listOf(
        Pair(1,2),
        Pair(1,1),
        Pair(1,1)
    )

    var r = l.groupBy { it }
    println()
}