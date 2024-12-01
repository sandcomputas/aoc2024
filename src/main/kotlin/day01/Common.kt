package dev.sondre.day01

fun readData(d: String): Pair<MutableList<Int>, MutableList<Int>> {
    val (l1, l2) = d
        .lines()
        .map {
            val row = it.split("""\s+""".toRegex())
            Pair(row.first().toInt(), row.last().toInt())
        }
        .unzip()
    return Pair(l1.toMutableList(), l2.toMutableList())
}