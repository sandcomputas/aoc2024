package dev.sondre.day10

import dev.sondre.day09.toNumbericInt

data class Pos(val row: Int, val col: Int)
abstract class Tile {
    abstract val value: Int
    abstract fun walk(): Int
    abstract fun walkUnique(): List<Pos>
    abstract val isStart: Boolean
    companion object {
        fun create(value: Char, pos: Pos, grid: Grid): Tile {
            return if (value == '.') IgnoreTile()
            else ActiveTile(value.toNumbericInt(), pos, grid)
        }
    }
}

class IgnoreTile : Tile() {
    override val value = 1000
    override fun walkUnique() = emptyList<Pos>()
    override fun walk() = 0
    override val isStart = false
}

class ActiveTile(override val value: Int, private val pos: Pos, private val grid: Grid) : Tile() {
    override fun walkUnique(): List<Pos> {
        if (value == 9) return listOf(pos)
        val checks = listOf(
            checkUnique(::upper),
            checkUnique(::lower),
            checkUnique(::left),
            checkUnique(::right)
        )
        return checks.flatten()
    }

    private fun checkUnique(direction: () -> Tile?): List<Pos> {
        val next = direction.invoke() ?: return emptyList()
        if (next.value == nextValue) return next.walkUnique()
        return emptyList()
    }

    override fun walk(): Int {
        if (value == 9) return 1

        val checks = listOf(
            check(::upper),
            check(::lower),
            check(::left),
            check(::right)
        )
        return checks.sum()
    }

    private fun check(direction: () -> Tile?): Int {
        val next = direction.invoke() ?: return 0
        if (next.value == nextValue) {
//            hasBeenReached = true
            return next.walk()
        }
        return 0
    }

    private fun upper(): Tile? = grid[pos.row - 1, pos.col]
    private fun lower(): Tile? = grid[pos.row + 1, pos.col]
    private fun left(): Tile? = grid[pos.row, pos.col - 1]
    private fun right(): Tile? = grid[pos.row, pos.col + 1]

    override val isStart: Boolean = value == 0

    private val nextValue: Int
        get() = value + 1
}

class Grid(raw: String) {
    private val map: List<List<Tile>> = raw.lines()
        .mapIndexed { indexRow, row ->
            row.mapIndexed { indexCol, t ->
                Tile.create(t, Pos(indexRow, indexCol), this)
            }
        }

    val allStarts: List<Tile> = map.flatten().filter { tile -> tile.isStart }

    operator fun get(row: Int, col: Int): Tile? {
        return try {
            map[row][col]
        } catch (_: IndexOutOfBoundsException) {
            null
        }
    }
}