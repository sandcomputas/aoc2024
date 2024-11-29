package dev.sondre.day32

import dev.sondre.Part
import dev.sondre.skipFirst

fun readLine2(data: String): Long {
   return data.split("""\s+""".toRegex())
       .toList()
       .skipFirst()
       .joinToString("") { it }
       .toLong()
}

class Part2(expRes: Int? = null) : Part(expRes) {
    override fun calc(data: String): Int {
        val time = readLine2(data.lines().first())
        val distance = readLine2(data.lines().last())
        return Race.fromPair(Pair(time, distance)).numberOfWaysToWin()
    }
}
