package dev.sondre

fun <T> Iterable<T>.skipN(n: Int): Iterable<T> {
    return this.filterIndexed { i, _ -> i != n } // Might not be super efficient, .drop(n) might be better
}

fun <T> Iterable<T>.skipFirst(): Iterable<T> {
    return this.drop(1)
}

fun Iterable<Int>.productOf(): Int {
    return this.reduce { acc, next -> acc * next }
}
