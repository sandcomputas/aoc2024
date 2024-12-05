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
    protected fun left(): Letter? = grid[position.row, position.col - 1]
    protected fun right(): Letter? = grid[position.row, position.col + 1]
    protected fun top(): Letter? = grid[position.row - 1, position.col]
    protected fun below(): Letter? = grid[position.row + 1, position.col]
    protected fun northWest(): Letter? = grid[position.row - 1, position.col - 1]
    protected fun northEast(): Letter? = grid[position.row - 1, position.col + 1]
    protected fun southEast(): Letter? = grid[position.row + 1, position.col + 1]
    protected fun southWest(): Letter? = grid[position.row + 1, position.col - 1]
    open fun count(): Int = 0
}