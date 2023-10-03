package org.example.algorithm.programmers.weekly_challenge

/**
 * 교점에 별 만들기
 *
 * line 원소가 최대 100,000이고 곱셈 계산을 하기 때문에 int 범위를 초과하여 Long을 사용해야 한다.
 */
class MakingStarJunction {

    private fun solution(line: Array<IntArray>): Array<String> {
        return makeGrid(findCrossPoint(line))
    }

    /**
     * 두 직선의 교점 구하기
     */
    private fun findCrossPoint(line: Array<IntArray>): List<Pair<Int, Int>> {
        val point = mutableListOf<Pair<Int, Int>>()
        for (i in 0 until line.size - 1) {
            for (j in i + 1 until line.size) {
                val (A, B, E) = line[i].map { it.toLong() }
                val (C, D, F) = line[j].map { it.toLong() }

                val denominator = A * D - B * C
                // 분모가 0이면 평행 또는 일치하므로 건너뛰기
                if (denominator == 0L) continue

                val numeratorX = B * F - E * D
                val numeratorY = E * C - A * F
                // 교차점이 정수가 아니면 건너뛰기
                if (numeratorX % denominator != 0L || numeratorY % denominator != 0L) continue

                val x = (numeratorX / denominator).toInt()
                val y = (numeratorY / denominator).toInt()
                point.add(Pair(x, y))
            }
        }
        return point
    }

    /**
     * 두 직선의 교점 구하기
     */
    private fun findCrossPoint1(line: Array<IntArray>): List<Pair<Int, Int>> {
        val point = mutableListOf<Pair<Int, Int>>()
        for (i in 0 until line.size - 1) {
            for (j in i + 1 until line.size) {
                val denominator = line[i][0].toLong() * line[j][1] - line[i][1].toLong() * line[j][0]
                if (denominator == 0L) continue

                val numeratorX = line[i][1].toLong() * line[j][2] - line[i][2].toLong() * line[j][1]
                val numeratorY = line[i][2].toLong() * line[j][0] - line[i][0].toLong() * line[j][2]
                if (numeratorX % denominator != 0L || numeratorY % denominator != 0L) continue

                val x = (numeratorX / denominator).toInt()
                val y = (numeratorY / denominator).toInt()
                point.add(Pair(x, y))
            }
        }
        return point
    }

    /**
     * 격자판 그리기
     */
    private fun makeGrid(crossPoint: List<Pair<Int, Int>>): Array<String> {
        val minX = crossPoint.minOf { it.first }
        val minY = crossPoint.minOf { it.second }
        val maxX = crossPoint.maxOf { it.first }
        val maxY = crossPoint.maxOf { it.second }

        val grid = Array(maxY - minY + 1) { CharArray(maxX - minX + 1) { '.' } }
        for (i in crossPoint.indices) {
            grid[maxY - crossPoint[i].second][crossPoint[i].first - minX] = '*'
        }

        return Array(grid.size) { String(grid[it]) }
    }

    fun case1() {
        val result = solution(
            arrayOf(
                intArrayOf(2, -1, 4),
                intArrayOf(-2, -1, 4),
                intArrayOf(0, -1, 1),
                intArrayOf(5, -8, -12),
                intArrayOf(5, 8, 12)
            )
        )
        println("case1 result: ${result.joinToString(",")}, ${result.joinToString(",") == "....*....,.........,.........,*.......*,.........,.........,.........,.........,*.......*"}")
    }

    fun case2() {
        val result = solution(
            arrayOf(
                intArrayOf(0, 1, -1),
                intArrayOf(1, 0, -1),
                intArrayOf(1, 0, 1)
            )
        )
        println("case2 result: ${result.joinToString(",")}, ${result.joinToString(",") == "*.*"}")
    }

    fun case3() {
        val result = solution(
            arrayOf(
                intArrayOf(1, -1, 0),
                intArrayOf(2, -1, 0)
            )
        )
        println("case3 result: ${result.joinToString(",")}, ${result.joinToString(",") == "*"}")
    }

    fun case4() {
        val result = solution(
            arrayOf(
                intArrayOf(1, -1, 0),
                intArrayOf(2, -1, 0),
                intArrayOf(4, -1, 0)
            )
        )
        println("case4 result: ${result.joinToString(",")}, ${result.joinToString(",") == "*"}")
    }

}