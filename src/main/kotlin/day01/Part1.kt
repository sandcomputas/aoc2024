package dev.sondre.day01

import dev.sondre.Part

class Part1 : Part() {
    override fun calc(data: String): Int {
        Thread.sleep(1000)
        return 42
    }
}
