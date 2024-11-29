package dev.sondre

fun <T> Iterable<T>.skipN(n: Int): Iterable<T> {
    return this.filterIndexed { i, _ -> i != n}
}

fun <T> Iterable<T>.skipFirst(): Iterable<T> {
    return this.skipN(0)
}

fun Iterable<Int>.productOf(): Int {
    return this.reduce { acc, next -> acc * next }
}
