package org.example.algorithm.programmers.example

class Tiling2N {
    private fun solution(n: Int): Int {
        val answer = IntArray(n + 1)
        answer[1] = 1
        answer[2] = 2
        for (i in 3..n)
            answer[i] = (answer[i - 1] + answer[i - 2]) % 1000000007
        return answer[n]
    }

    fun case1() {
        val result = solution(4)
        println("case1 result: $result, ${result == 5}")
    }
}