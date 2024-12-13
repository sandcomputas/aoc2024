package dev.sondre.day09

import dev.sondre.Part


fun Char.toNumbericInt() = this.toString().toInt()

abstract class BlockSpace {
    companion object {
        fun from(s: String): BlockSpace {
            return if (s.toIntOrNull() == null) {
                EmptySpace()
            } else UsedSpace(s.toInt())
        }

        fun from(id: Int): UsedSpace {
            return UsedSpace(id)
        }
    }
}

class UsedSpace(val id: Int) : BlockSpace()

class EmptySpace : BlockSpace()

fun List<BlockSpace>.isCompacted(): Boolean {
    for (bs in this) {
        if (bs is EmptySpace) return false
    }
    return true
}

fun compactDisk(disk: List<BlockSpace>): List<BlockSpace> {
    if (disk.isCompacted()) return disk.map { it as UsedSpace }
    val last = disk.last()
    val firstAvailableSpaceIndex = disk.indexOfFirst { it is EmptySpace }
    val newDisk = disk.toMutableList()
    newDisk[firstAvailableSpaceIndex] = last
    newDisk.removeLast()
    return compactDisk(newDisk)
}

fun checkSum(disk: List<UsedSpace>): Long {
    val multiplied = disk.mapIndexed { index, b -> index * b.id }
    var sum = 0L
    for (n in multiplied) {
        sum += n
    }
    return sum
}

class Part1(expRes: Int? = null) : Part(expRes) {
    override fun solve(data: String): Int {
        val (filesDiskMap, freeSpaceDiskMap) = data.chunked(2).map { Pair(it.first(), it.last()) }.unzip()
        val filesBlock: List<List<BlockSpace>> = filesDiskMap.mapIndexed { id, f ->
            (0 until f.toNumbericInt()).map { BlockSpace.from(id) }
        }
        val freeSpaceBlock = freeSpaceDiskMap.mapIndexed { index, f ->
            // Do not need to render trailing empty disk space
            if (index == freeSpaceDiskMap.size - 1) "" else ".".repeat(f.toNumbericInt())
        }.map { it.toCharArray().map { BlockSpace.from(it.toString()) } }

        val disk = (filesBlock zip freeSpaceBlock).map { listOf(it.first, it.second) }.flatten().flatten()
        val compactedDisk = compactDisk(disk)
        println(checkSum(compactedDisk.map { it as UsedSpace }))
        return 0
    }
}            