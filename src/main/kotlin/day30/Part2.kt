package dev.sondre.day30

import dev.sondre.Part

class Part2(expRes: Int? = null) : Part(expRes) {
    override fun solve(data: String): Int {
        val numbers = data.lines().map { it.toInt() }
        var current = 0
        val fqs = mutableMapOf(Pair(current, 1))

        var keepGoing = true

        while(keepGoing) {
            for (n in numbers) {
                current += n
                fqs[current] = fqs.getOrDefault(current, 0) + 1
                if (fqs[current] == 2) {
                    keepGoing = false
                    break
                }
            }
        }
        return fqs.maxBy { it.value }.key
    }
}            