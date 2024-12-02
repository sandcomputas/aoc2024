package dev.sondre.day01

fun List<String>.toIntPair(): Pair<Int, Int> {
    check(this.size == 2) { "List cannot be converted to pair, must be of exact length 2!" }
    return Pair(this.first().toInt(), this.last().toInt())
}

fun readData(d: String): Pair<MutableList<Int>, MutableList<Int>> {
    val (l1, l2) = d
        .lines()
        .map { row ->
            row.split("""\s+""".toRegex())
                .toIntPair()
        }
        .unzip()
    return Pair(l1.toMutableList(), l2.toMutableList())
}
