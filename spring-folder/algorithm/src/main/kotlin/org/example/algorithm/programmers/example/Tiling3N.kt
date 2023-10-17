package org.example.algorithm.programmers.example

class Tiling3N {
    private fun solution(n: Int): Long {
        if (n % 2 != 0) return 0

        val dp = LongArray(n + 1)
        dp[0] = 1
        dp[2] = 3
        for (i in 4..n step 2) {
            dp[i] = dp[i - 2] * 3
            for (j in i - 4 downTo 0 step 2)
                dp[i] = dp[i] + dp[j] * 2
            dp[i] = dp[i] % 1000000007
        }
        return dp[n]
    }

    private fun solution1(n: Int): Long {
        if (n % 2 != 0) return 0

        val mod = 1000000007

        val dp = LongArray(n + 1)
        dp[0] = 1
        dp[2] = 3
        for (i in 4..n step 2) {
            dp[i] = (dp[i - 2] * 4 % mod - dp[i - 4] % mod + mod) % mod
        }
        return dp[n]
    }

    fun case1() {
        val result = solution1(4)
        println("case1 result: $result, ${result == 11L}")
    }

    fun case2() {
        val result = solution1(6)
        println("case2 result: $result, ${result == 41L}")
    }

    fun case3() {
        val result = solution1(8)
        println("case3 result: $result, ${result == 153L}")
    }

    fun case4() {
        val result = solution1(10)
        println("case4 result: $result, ${result == 571L}")
    }

    fun case5() {
        val result = solution1(12)
        println("case5 result: $result, ${result == 2131L}")
    }

    fun case6() {
        val result = solution1(14)
        println("case6 result: $result, ${result == 7953L}")
    }

    fun case7() {
        val result = solution1(5000)
        println("case7 result: $result, ${result == 658712818L}")
    }
}