package dev.sondre.day04

import dev.sondre.Part

abstract class Letter(position: Position, grid: Grid) : BaseLetter(position, grid){

    override fun search(direction: BaseLetter.() -> BaseLetter?): Boolean { // Receiver function does the trick!
        val d = this.direction()
        if (d != null && d.value == nextValue) {
            return d.search(direction)
        }
        return false
    }

    fun searchLeft(): Boolean = search { this.left() }
    fun searchRight(): Boolean = search { this.right() }
    fun searchTop(): Boolean = search { this.top() }
    fun searchBelow(): Boolean = search { this.below() }
    fun searchNorthWest(): Boolean = search { this.northWest() }
    fun searchNorthEast(): Boolean = search { this.northEast() }
    fun searchSouthEast(): Boolean = search { this.southEast() }
    fun searchSouthWest(): Boolean = search { this.southWest() }
}

class X(position: Position, grid: Grid) : Letter(position, grid) {
    override val value = 'X'
    override val nextValue = 'M'
    override fun count(): Int {
        val searches = listOf(
            ::searchLeft,
            ::searchRight,
            ::searchTop,
            ::searchBelow,
            ::searchNorthWest,
            ::searchNorthEast,
            ::searchSouthEast,
            ::searchSouthWest
        )
        return searches.map { it.invoke() }.count { it }
    }
}

class M(position: Position, grid: Grid) : Letter(position, grid) {
    override val value = 'M'
    override val nextValue = 'A'
}

class A(position: Position, grid: Grid) : Letter(position, grid) {
    override val value = 'A'
    override val nextValue = 'S'
}

class S(position: Position, grid: Grid) : Letter(position, grid) {
    override val value = 'S'
    override val nextValue = '-'
    override fun search(direction: BaseLetter.() -> BaseLetter?): Boolean = true
}

data class Position(val row: Int, val col: Int)

class Part1(expRes: Int? = null) : Part(expRes) {
    override fun solve(data: String): Int {
        return Grid(data).flat().sumOf { it.count() }
    }
}