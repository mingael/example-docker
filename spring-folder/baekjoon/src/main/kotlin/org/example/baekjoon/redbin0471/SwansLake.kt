package org.example.baekjoon.redbin0471

import java.util.*
import kotlin.collections.ArrayDeque

/**
 * 백조의 호수
 * - 너비 우선 탐색(BFS, Breadth-First Search)과 큐(Queue)를 활용한 문제
 *
 * memory: 312488 KB
 * time: 1500 ms
 */
class SwansLake {

    companion object {
        private const val WATER = '.'
        private const val ICE_SHEET = 'X'
        private const val SWAN = 'L'
    }

    private var R = 0
    private var C = 0

    private lateinit var lake: Array<Array<Char>>
    private var waterQueue = ArrayDeque<Pair<Int, Int>>()   // 물 영역 (물로 변한 빙판)
    private var swan = Pair(0, 0)   // 기다리는 백조 위치
    private var swanQueue = ArrayDeque<Pair<Int, Int>>()    // 백조 영역
    private lateinit var swanVisible: Array<Array<Boolean>> // 백조 방문 여부

    // 빙판 녹는 거리
    private val meltingDistance = arrayOf(Pair(-1, 0), Pair(0, -1), Pair(1, 0), Pair(0, 1))

    private var flag = false

    fun example1() {
        init()

        println("========== 초기 ==========")
        lake.forEach { arr -> arr.forEach { print(it) }; println() }

        var day = 0
        while (true) {
            // 백조 이동
            moveSwan()
            // 백조들이 만나면 종료
            if (flag) break
            // 빙판 녹이기
            meltIce()

            println("========== ${++day}일 ==========")
            lake.forEachIndexed { r, arr ->
                arr.forEachIndexed { c, value ->
                    val s = swanQueue.firstOrNull { it.first == r && it.second == c }?.let { "&" } ?: value
                    print(s)
                }; println()
            }
        }

        println("========== 완료 ==========")
        lake.forEach { arr -> arr.forEach { print(it) }; println() }

        println(day)
    }

    /**
     * 초기화
     */
    private fun init() {
        System.`in`.bufferedReader().run {
            StringTokenizer(readln()).run {
                R = nextToken().toInt()
                C = nextToken().toInt()
            }

            // 호수 초기 상태
            lake = Array(R) { r ->
                val str = StringTokenizer(readln()).nextToken()
                Array(C) { c ->
                    // 빙판을 제외한 모든 지점을 물 큐에 넣기
                    if (str[c] != ICE_SHEET) waterQueue.add(Pair(r, c))
                    // 백조 위치
                    if (str[c] == SWAN) swanQueue.add(Pair(r, c))
                    // 호수 상태
                    str[c]
                }
            }

            // 기다릴 백조
            swan = swanQueue.removeFirst()
            // 백조 방문지
            swanVisible = Array(R) { Array(C) { false } }
        }
    }

    /**
     * 백조 이동하기
     * - 이동할 백조 위치에서부터 시작
     * - 현재 위치가 기다리는 백조 위치와 일치하면 계산 종료
     * - 인접한 방향(대각선 제외)이 빙판이면 다음 큐(nextQueue), 아니면 재탐색을 위해 swanQueue에 다시 담는다.
     * - 다만 백조가 중복 탐색하지 않도록 방문 지점을 따로 저장(swanVisible)하여 이미 방문한 지점은 건너뛴다.
     */
    private fun moveSwan() {
        val nextQueue = ArrayDeque<Pair<Int, Int>>()

        while (swanQueue.isNotEmpty() && !flag) {
            val moveSwan = swanQueue.removeFirst()
            // 백조 만남
            if (swan.first == moveSwan.first && swan.second == moveSwan.second) {
                flag = true
                break
            }
            meltingDistance.forEach move@{
                val moveRow = moveSwan.first + it.first
                val moveCol = moveSwan.second + it.second

                if (!isMove(moveRow, moveCol)) return@move
                if (swanVisible[moveRow][moveCol]) return@move
                swanVisible[moveRow][moveCol] = true

                if (lake[moveRow][moveCol] == ICE_SHEET) nextQueue.add(Pair(moveRow, moveCol))
                else swanQueue.add(Pair(moveRow, moveCol))
            }
        }

        swanQueue = nextQueue
    }

    /**
     * 빙판 녹이기
     * - 초기 물에서부터 시작
     * - 인접한 방향(대각선 제외)이 빙판이면 해당 지점을 녹이고 녹인물 큐(meltQueue)에 넣는다. (BFS 수행)
     * - 녹인물 큐(meltQueue)를 물 큐(waterQueue)에 갱신한다.
     * - 함수가 실행될 때마다 이전에 탐색을 중지한 지점(빙판이 녹은 물)에서부터 BFS를 수행하게 된다.
     * - 기존 물은 탐색하고나면 다시 방문하지 않기 때문에 탐색 횟수를 줄일 수 있다. (중복 탐색 제거)
     */
    private fun meltIce() {
        // 녹여서 물이된 빙판
        val meltQueue = ArrayDeque<Pair<Int, Int>>()

        // 기존 물 영역
        while (waterQueue.isNotEmpty()) {
            val currNode = waterQueue.removeFirst()

            // 물과 인접한 빙판 녹이기
            meltingDistance.forEach move@{
                val moveRow = currNode.first + it.first
                val moveCol = currNode.second + it.second

                if (!isMove(moveRow, moveCol)) return@move
                if (lake[moveRow][moveCol] != ICE_SHEET) return@move

                lake[moveRow][moveCol] = WATER
                meltQueue.add(Pair(moveRow, moveCol))
            }
        }

        // 녹여서 새로 생성된 물 지점
        waterQueue = meltQueue
    }

    /**
     * Overflow 방지
     */
    private fun isMove(row: Int, col: Int): Boolean {
        return row > -1 && col > -1 && row < R && col < C
    }
}