package org.example.algorithm.programmers.example

class FibonacciNumber {

    private fun solution(n: Int): Int {
        val answer = IntArray(n + 1)
        for (i in 0..n) {
            if (i < 2) {
                answer[i] = i
                continue
            }
            answer[i] = (answer[i - 1] + answer[i - 2]) % 1234567
        }
        return answer[n]
    }

    private fun solution1(n: Int): Int {
        val answer = IntArray(n + 1) { 0 }
        answer[1] = 1
        for (i in 2..n) answer[i] = (answer[i - 1] + answer[i - 2]) % 1234567
        return answer[n]
    }

    private fun solution2(n: Int): Int {
        return fibonacci(n, 0, 1)
    }

    private tailrec fun fibonacci(n: Int, a: Int, b: Int): Int {
        return if (n == 0) a else fibonacci(n - 1, (a + b) % 1234567, a)
    }

    private fun fibonacci(n: Int): Int {
        if (n < 2) return n
        return fibonacci(n - 1) + fibonacci(n - 2)
    }

    fun case1() {
        val result = solution(0)
        println("case1 result: $result, ${result == 0}")
    }

    fun case2() {
        val result = solution(1)
        println("case2 result: $result, ${result == 1}")
    }

    fun case3() {
        val result = solution(2)
        println("case3 result: $result, ${result == 1}")
    }

    fun case4() {
        val result = solution(3)
        println("case4 result: $result, ${result == 2}")
    }
}