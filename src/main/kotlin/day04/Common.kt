package dev.sondre.day04

abstract class BaseLetter(private val position: Position, private val grid: Grid) {
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
    fun left(): BaseLetter? = grid[position.row, position.col - 1]
    fun right():BaseLetter? = grid[position.row, position.col + 1]
    fun top():BaseLetter? = grid[position.row - 1, position.col]
    fun below():BaseLetter? = grid[position.row + 1, position.col]
    fun northWest():BaseLetter? = grid[position.row - 1, position.col - 1]
    fun northEast():BaseLetter? = grid[position.row - 1, position.col + 1]
    fun southEast():BaseLetter? = grid[position.row + 1, position.col + 1]
    fun southWest():BaseLetter? = grid[position.row + 1, position.col - 1]
    open fun count(): Int = 0
    abstract fun search(direction: BaseLetter.() -> BaseLetter?): Boolean
}

class Grid(raw: String) {
    private val grid: List<List<BaseLetter>> = raw
        .lines()
        .mapIndexed { indexRow, row ->
            row.mapIndexed { indexColumn, char ->
                val pos = Position(indexRow, indexColumn)
                BaseLetter.from(char, pos, this)
            }
        }

    operator fun get(x: Int, y: Int): BaseLetter? {
        try {
            val l = grid[x][y]
            return l
        } catch (e: IndexOutOfBoundsException) {
            return null
        }
    }

    fun flat(): List<BaseLetter> = grid.flatten()
}