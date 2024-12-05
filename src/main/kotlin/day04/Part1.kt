package dev.sondre.day04

import dev.sondre.Part

class PuzzleBoard(private val raw: String) {
    private val grid: List<List<Char>> = raw.lines().toList().map { it.toCharArray().toList() }

    fun horizontal(): List<List<Char>> {
        val leftToRight = grid
        val rightToLeft = leftToRight.reversed()
//        return leftToRight + rightToLeft // TODO: Tror ikke dette fungerer
        return leftToRight
    }

    fun vertical(): List<List<Char>> {
        val vertGrid = (0..grid.first().size).map { mutableListOf<Char>() }.toMutableList()
        grid.forEachIndexed { indexRow, row ->
            row.forEachIndexed { indewColumn, char ->
                vertGrid[indewColumn].add(char)
            }
        }
        return vertGrid
    }

    fun diagonal() {

    }

    fun search() {}
}

// ------ above is old


abstract class Letter(private val position: Position, private val grid: Grid) {
    companion object {
        fun from(char: Char, position: Position, grid: Grid): Letter {
            return when (char) {
                'X' -> X(position, grid)
                'M' -> M(position, grid)
                'A' -> A(position, grid)
                'S' -> S(position, grid)
                else -> throw Exception("Illegal character")
            }
        }
    }

    abstract val value: Char
    abstract val nextValue: Char
    fun left(): Letter? = grid[position.row, position.col - 1]
    fun right(): Letter? = grid[position.row, position.col + 1]
    fun top(): Letter? = grid[position.row - 1, position.col]
    fun below(): Letter? = grid[position.row + 1, position.col]
    fun northWest(): Letter? = grid[position.row - 1, position.col - 1]
    fun southEast(): Letter? = grid[position.row + 1, position.col + 1]
    open fun count(): Int = 0
    open fun searchLeft(): Boolean {
        val l = left()
        if (l != null && l.value == nextValue) {
            return l.searchLeft()
        }
        return false
    }
}

class X(position: Position, grid: Grid) : Letter(position, grid) {
    override val value = 'X'
    override val nextValue = 'M'
    override fun count(): Int {
        return if (searchLeft()) 1 else 0
    }
//
//        val l1 = left()
//        if (l1 != null && l1.value == 'M') {
//            val l2 = l1.left()
//            if (l2 != null && l2.value == 'A') {
//                val l3 = l2.left()
//                if (l3 != null && l3.value == 'S') {
//                    return true
//                }
//            }
//        }
//        return false
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
    override fun searchLeft(): Boolean {
        return true
    }
}

data class Position(val row: Int, val col: Int)

class Grid(raw: String) {
    private val grid: List<List<Letter>> = raw
        .lines()
        .mapIndexed { indexRow, row ->
            row.mapIndexed { indexColumn, char ->
                val pos = Position(indexRow, indexColumn)
                Letter.from(char, pos, this)
            }
        }

    operator fun get(x: Int, y: Int): Letter? {
        try {
            val l = grid[x][y]
            return l
        } catch (e: IndexOutOfBoundsException) {
            return null
        }
    }

    fun flat(): List<Letter> {
        return grid.flatten()
    }
}


class Part1(expRes: Int? = null) : Part(expRes) {
    override fun solve(data: String): Int {
        /**
         * Naiv løsning: for hver retning (hor, vert, diag) loop igjennom og
         * let etter de fire bokstavene i ordet. Men bare plukk den hvis den ikke allerede er i listen
         */

        val sum = Grid(data).flat().sumOf { it.count() }

        return sum
    }
}            