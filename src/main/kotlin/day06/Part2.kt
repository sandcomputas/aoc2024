package dev.sondre.day06

import dev.sondre.Part

/**
 * Kanskje bruke svaret fra første til å lage en syklisk graf. De punktene som ikke besøkes i den første grafen er
 * uansett ikke relevant i denne oppgaven fordi vakten vil aldri gå der.
 *
 * Fjern så en og en av alle nodene og sjekk om den blir syklisk.
 *
 * Er kanskje bare de som er besøkt flere ganger som er relevant å sjekke??
 */

class Part2(expRes: Int? = null) : Part(expRes) {
    override fun solve(data: String): Int {
        val l = Lab(data)
        l.walk()
        val visitedPositions = l.visited

        var cycles = 0
        var test = 0
        visitedPositions.parallelStream().forEach {
            val l2 = Lab(data)
            l2.addBlock(it)
            l2.walk()
            if (l2.hasCycle()) cycles++
            println("Attempt ${++test} (cycles found: $cycles)")
        }

        // Graph?
        return cycles -1
    }
}            