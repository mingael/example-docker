package org.example.algorithm.baekjoon.redbin0471

/**
 * 이항 계수
 */
class BinomialCoefficient {

    fun run() {
        val m = readln().toInt()
        val result = factorial(m)
        println()
        println(result)
    }

    private val result = mutableListOf<Pair<Int, Int>>()
    private fun example(m: Int) {
        (0..m+1).forEach { n ->
            (0..n+1).forEach { k->
                if (factorial(n) == m) result.add(Pair(n, k))
            }
        }
    }

    private fun factorial(n: Int): Int {
        var acc = n
        (1 until n).forEach { acc *= it }
        return acc
    }
}