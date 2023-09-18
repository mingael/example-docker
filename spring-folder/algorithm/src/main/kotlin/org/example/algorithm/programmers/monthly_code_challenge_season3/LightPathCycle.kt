package org.example.algorithm.programmers.monthly_code_challenge_season3

/**
 * 알고리즘 보다는 문제에 대한 이해도와 배열 활용도를 요구하는 문제
 * 1. 빛을 임의의 위치(네 방향, 상하좌우)에서 쐈을 때 사이클 하나가 어떻게 발생하는지 이해하기.
 * - 빛은 주어진 격자(grid) 요소(SRL)에 따라 이동하다가 원래 위치로 되돌아온다.
 * -- 빛이 제자리로 돌아오면 사이클 하나가 종료된다.
 * -- 다른 사이클이 통과한 경로는 이용하지 않는다. (중복되는 순간부터 이전 사이클과 동일해진다.)
 * - 무한 이동하지 않도록 격자 요소마다 네 방향을 들렀는지 여부를 알고 있어야 한다.
 * => [x좌표][y좌표][4방향]을 가지는 3차원 배열 변수 설정 (visible)
 * 2. 격자 요소마다 빛을 네 방향 모두 쏴봐야하므로 회전 방향을 정한다.
 * - 회전 방향을 정하여 x, y 이동 좌표 변수 설정 (*_axis_direction)
 * 3. 회전 했을 때 좌표가 변하는 규칙 찾기
 * - 빛이 격자 밖으로 이동하면 반대쪽 끝으로 돌아온다. 즉, 원래 위치로 돌아온다.
 */
class LightPathCycle {

    companion object {
        // 시계 방향으로 하, 좌, 상, 우
        private val X_AXIS_DIRECTION = arrayOf(0, -1, 0, 1)
        private val Y_AXIS_DIRECTION = arrayOf(-1, 0, 1, 0)

        // 빛 방향은 네개
        private const val DIRECTION_COUNT = 4
    }

    private var rowCount: Int = 0
    private var colCount: Int = 0
    private lateinit var visible: Array<Array<BooleanArray>>

    private fun solution(grid: Array<String>): IntArray {
        rowCount = grid.size
        colCount = grid[0].length

        // 방문지
        visible = Array(rowCount) { Array(colCount) { BooleanArray(DIRECTION_COUNT) { false } } }

        val answer = ArrayList<Int>()

        // grid 요소에 대해
        for (y in 0 until rowCount) {
            for (x in 0 until colCount) {
                // 상하좌우 모든 방향에서 빛 쏘기
                for (d in 0 until DIRECTION_COUNT) {
                    if (visible[y][x][d]) continue
                    answer.add(lightCycle(grid, y, x, d))
                }
            }
        }

        // 오름차순 정렬
        return answer.sorted().toIntArray()
    }

    private fun lightCycle(grid: Array<String>, row: Int, col: Int, direction: Int): Int {
        var lightLength = 0
        var (y, x, d) = Triple(row, col, direction)
        while (!visible[y][x][d]) {
            lightLength++
            visible[y][x][d] = true

            d = rotation(grid[y][x], d)
            y = (y + Y_AXIS_DIRECTION[d] + rowCount) % rowCount
            x = (x + X_AXIS_DIRECTION[d] + colCount) % colCount
        }
        return lightLength
    }

    /**
     * 회전
     *
     * @param direction 회전방향
     * @param entryDirection 진입방향
     */
    private fun rotation(direction: Char, entryDirection: Int): Int {
        return when (direction) {
            'L' -> (entryDirection + 3) % 4 // 좌회전: xDir Index 3
            'R' -> (entryDirection + 1) % 4 // 우회전: xDir Index 1
            else -> entryDirection // 직진
        }
    }

    fun case1() {
        val result = solution(arrayOf("SR", "LR"))
        println("case1: ${result.joinToString(",")}")
    }

    fun case2() {
        val result = solution(arrayOf("S"))
        println("case2: ${result.joinToString(",")}")
    }

    fun case3() {
        val result = solution(arrayOf("R", "R"))
        println("case3: ${result.joinToString(",")}")
    }

}