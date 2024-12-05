package dev.sondre.day04

import dev.sondre.Part

abstract class LetterPart2(position: Position, grid: Grid2) : BaseLetter(position, grid) {
    open fun isXCenter(): Boolean = false
}

class AX(position: Position, grid: Grid2) : LetterPart2(position, grid) {
    override val value = 'A'
    override val nextValue = '-' // Only really relevant for part 1

    override fun search(direction: BaseLetter.() -> BaseLetter?): Boolean = TODO("not needed for part to, consider refactoring to avoid this")

    override fun isXCenter(): Boolean = (searchLeftDownRight() && searchLeftUpRight())

    private fun searchLeftDownRight(): Boolean { // "\"
        val nw = northWest() ?: return false
        val se = southEast() ?: return false
        return (nw.value == 'S' && se.value == 'M') || (nw.value == 'M' && se.value == 'S')
    }

    private fun searchLeftUpRight(): Boolean { // "/"
        val sw = southWest() ?: return false
        val ne = northEast() ?: return false
        return (sw.value == 'S' && ne.value == 'M') || (sw.value == 'M' && ne.value == 'S')
    }
}

class MX(position: Position, grid: Grid2) : LetterPart2(position, grid) {
    override val value = 'M'
    override val nextValue = '-' // Only really relevant for part 1
    override fun search(direction: BaseLetter.() -> BaseLetter?): Boolean = TODO("Not yet implemented")
}

class SX(position: Position, grid: Grid2) : LetterPart2(position, grid) {
    override val value = 'S'
    override val nextValue = '-' // Only really relevant for part 1
    override fun search(direction: BaseLetter.() -> BaseLetter?): Boolean = TODO("Not yet implemented")
}

class XX(position: Position, grid: Grid2) : LetterPart2(position, grid) {
    override val value = 'X'
    override val nextValue = '-' // Only really relevant for part 1
    override fun search(direction: BaseLetter.() -> BaseLetter?): Boolean = TODO("Not yet implemented")
}

class Grid2(raw: String) : Grid(raw) {
    override val grid: List<List<LetterPart2>> = raw
        .lines()
        .mapIndexed { indexRow, row ->
            row.mapIndexed { indexColumn, char ->
                val pos = Position(indexRow, indexColumn)
                BaseLetter.fromPart2(char, pos, this)
            }
        }
    fun flat(): List<LetterPart2> = grid.flatten()
}

class Part2(expRes: Int? = null) : Part(expRes) {
    override fun solve(data: String): Int {

        return Grid2(data).flat().count {it.isXCenter()}}
}            