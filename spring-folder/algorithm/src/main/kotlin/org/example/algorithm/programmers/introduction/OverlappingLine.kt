package org.example.algorithm.programmers.introduction

/**
 * 겹치는 선분의 길이
 */
class OverlappingLine {

    fun run(lines: Array<IntArray>): Int {
        // 점의 범위가 -100 <= a < b <= 100 이므로 총 200+1(0)개 점을 가지는 배열을 만든다.
        val count = Array(201) { 0 }
        // 선분이 지나가는 모든 점들을 표시한다.
        lines.forEach {
            // 선분 길이는 시작점부터 끝점까지 존재하는 전체 점 개수에서 1개를 뺀 값이다
            // 그러므로 반복문에서 끝점은 제외한다.
            for (i in it[0] until it[1]) count[i + 100]++
        }
        // 겹치는 점 개수 = 겹치는 선분 길이
        return count.count { it >= 2 }
    }

    fun case1() {
        val lines = arrayOf(intArrayOf(0, 1), intArrayOf(2, 5), intArrayOf(3, 9))
        val result = run(lines)
        println("case1: $result, ${result == 2}")
    }

    fun case2() {
        val lines = arrayOf(intArrayOf(-1, 1), intArrayOf(1, 3), intArrayOf(3, 9))
        val result = run(lines)
        println("case2: $result, ${result == 0}")
    }

    fun case3() {
        val lines = arrayOf(intArrayOf(0, 5), intArrayOf(3, 9), intArrayOf(1, 10))
        val result = run(lines)
        println("case3: $result, ${result == 8}")
    }
}