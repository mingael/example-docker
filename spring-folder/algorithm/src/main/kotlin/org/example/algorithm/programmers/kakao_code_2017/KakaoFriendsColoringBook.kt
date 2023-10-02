package org.example.algorithm.programmers.kakao_code_2017

/**
 * 카카오 프렌즈 컬러링북
 */
class KakaoFriendsColoringBook {

    private val distanceX = intArrayOf(0, 0, -1, 1)
    private val distanceY = intArrayOf(-1, 1, 0, 0)

    private lateinit var visited: Array<BooleanArray>
    private var count = 0

    private fun solution(m: Int, n: Int, picture: Array<IntArray>): IntArray {
        visited = Array(m) { BooleanArray(n) { false } }

        val color = mutableListOf<Int>()

        for (y in 0 until m) {
            for (x in 0 until n) {
                if (picture[y][x] == 0 || visited[y][x]) continue
                dfs(n, m, x, y, picture[y][x], picture)
                color.add(count)
                count = 0
            }
        }

        color.sort()
        return intArrayOf(color.size, color.last())
    }

    private fun dfs(n: Int, m: Int, x: Int, y: Int, target: Int, picture: Array<IntArray>) {
        visited[y][x] = true
        count++

        for (i in 0..3) {
            val nextY = y + distanceY[i]
            val nextX = x + distanceX[i]

            if (nextY >= m || nextX >= n || nextY < 0 || nextX < 0) continue
            if (visited[nextY][nextX] || picture[nextY][nextX] == 0) continue
            if (target != picture[nextY][nextX]) continue

            dfs(n, m, nextX, nextY, target, picture)
        }
    }

    fun case1() {
        val result = solution(
            13,
            16,
            arrayOf(
                intArrayOf(0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0),
                intArrayOf(0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0),
                intArrayOf(0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0),
                intArrayOf(0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0),
                intArrayOf(0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0),
                intArrayOf(0, 1, 1, 1, 1, 3, 1, 1, 1, 1, 3, 1, 1, 1, 1, 0),
                intArrayOf(0, 1, 1, 1, 3, 1, 3, 1, 1, 3, 1, 3, 1, 1, 1, 0),
                intArrayOf(0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0),
                intArrayOf(0, 1, 2, 2, 2, 1, 1, 1, 1, 1, 1, 2, 2, 2, 1, 0),
                intArrayOf(0, 1, 1, 1, 1, 1, 3, 1, 1, 3, 1, 1, 1, 1, 1, 0),
                intArrayOf(0, 0, 1, 1, 1, 1, 1, 3, 3, 1, 1, 1, 1, 1, 0, 0),
                intArrayOf(0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0),
                intArrayOf(0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0)
            )
        )
        println("case1 - result: ${result.joinToString(",")}, ${result[0] == 12} ${result[1] == 120}")
    }

    fun case2() {
        val result = solution(
            6,
            4,
            arrayOf(
                intArrayOf(1, 1, 1, 0),
                intArrayOf(1, 2, 2, 0),
                intArrayOf(1, 0, 0, 1),
                intArrayOf(0, 0, 0, 1),
                intArrayOf(0, 0, 0, 3),
                intArrayOf(0, 0, 0, 3)
            )
        )
        println("case1 - result: ${result.joinToString(",")}, ${result[0] == 4} ${result[1] == 5}")
    }

}