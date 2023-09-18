package org.example.algorithm.programmers.example

import kotlin.math.pow

/**
 * 점대칭
 *
 *
 * 테스트 1 〉	통과 (8.21ms, 62.3MB)
 * 테스트 2 〉	통과 (12.07ms, 61.4MB)
 * 테스트 3 〉	통과 (6.39ms, 61.1MB)
 * 테스트 4 〉	통과 (12.56ms, 60.7MB)
 * 테스트 5 〉	통과 (6.76ms, 60.8MB)
 * 테스트 6 〉	통과 (7.74ms, 63.5MB)
 * 테스트 7 〉	통과 (11.57ms, 61.6MB)
 * 테스트 8 〉	통과 (7.71ms, 61.4MB)
 * 테스트 9 〉	통과 (8.12ms, 61.8MB)
 * 테스트 10 〉	통과 (7.44ms, 63.5MB)
 * 테스트 11 〉	통과 (6.95ms, 61.9MB)
 * 테스트 12 〉	통과 (7.79ms, 62.7MB)
 * 테스트 13 〉	통과 (10.31ms, 62.7MB)
 * 테스트 14 〉	통과 (6.15ms, 63.3MB)
 * 테스트 15 〉	통과 (11.84ms, 62MB)
 * 테스트 16 〉	통과 (10.43ms, 61.1MB)
 * 테스트 17 〉	통과 (10.12ms, 62.5MB)
 * 테스트 18 〉	통과 (7.25ms, 62MB)
 * 테스트 19 〉	통과 (8.83ms, 62MB)
 * 테스트 20 〉	통과 (11.00ms, 62MB)
 * 테스트 21 〉	통과 (10.40ms, 62.2MB)
 * 테스트 22 〉	통과 (11.15ms, 61.1MB)
 * 테스트 23 〉	통과 (6.94ms, 61.3MB)
 * 테스트 24 〉	통과 (11.44ms, 62.1MB)
 * 테스트 25 〉	통과 (8.19ms, 62MB)
 * 테스트 26 〉	통과 (7.30ms, 62.4MB)
 * 테스트 27 〉	통과 (6.33ms, 61.5MB)
 * 테스트 28 〉	통과 (11.22ms, 62.8MB)
 * 테스트 29 〉	통과 (6.26ms, 61.8MB)
 * 테스트 30 〉	통과 (7.13ms, 61.5MB)
 * 테스트 31 〉	통과 (8.26ms, 61.9MB)
 * 테스트 32 〉	통과 (7.55ms, 62.4MB)
 * 테스트 33 〉	통과 (10.07ms, 61.4MB)
 * 테스트 34 〉	통과 (10.93ms, 62.2MB)
 * 테스트 35 〉	통과 (8.50ms, 60.4MB)
 * 테스트 36 〉	통과 (7.48ms, 62.6MB)
 */
class Billiards {

    /**
     * 점선 거리^2 = 대각선^2 = 가로^2 + 세로^2
     */
    private fun run(m: Int, n: Int, startX: Int, startY: Int, balls: Array<IntArray>): IntArray {
        val answer = ArrayList<Int>()

        balls.forEach { ball ->
            val x = ball[0]
            val y = ball[1]

            val list = ArrayList<Pair<Int, Int>>()
            if (x != startX || y >= startY) list.add(Pair(x, y * -1))
            if (x != startX || y <= startY) list.add(Pair(x, 2 * n - y))
            if (y != startY || x >= startX) list.add(Pair(x * -1, y))
            if (y != startY || x <= startX) list.add(Pair(2 * m - x, y))

            // 기울기 최소값
            var min = Integer.MAX_VALUE
            list.forEach {
                val diffX = (startX - it.first).toDouble()
                val diffY = (startY - it.second).toDouble()
                val d = (diffX.pow(2) + diffY.pow(2)).toInt()
                min = minOf(min, d)
            }
            answer.add(min)
        }

        return answer.toIntArray()
    }

    fun case1() {
        val result = run(10, 10, 3, 7, arrayOf(intArrayOf(7, 7), intArrayOf(2, 7), intArrayOf(7, 3)))
        println("case1: ${result.joinToString(",")}")
    }

}