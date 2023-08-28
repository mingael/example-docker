package org.example.baekjoon.redbin0471

import java.util.*
import kotlin.collections.ArrayDeque

/**
 * 백조의 호수
 * - 너비 우선 탐색(BFS, Breadth-First Search)과 큐(Queue)를 활용한 문제
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
    private var swanQueue = ArrayDeque<Pair<Int, Int>>()    // 백조 위치

    // 빙판 녹는 거리
    private val meltingDistance = arrayOf(Pair(-1, 0), Pair(0, -1), Pair(1, 0), Pair(0, 1))

    fun example1() {
        init()

        println("전")
        lake.forEach { arr -> arr.forEach { print(it) }; println() }


        (1..3).forEach { day ->
            meltIce()
            println("후 $day")
            lake.forEach { arr -> arr.forEach { print(it) }; println() }
        }
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
                    str[c]
                }
            }
        }
    }

    private fun moveSwan() {
        // TODO()
    }

    /**
     * 빙판 녹이기
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
            val row = currNode.first
            val col = currNode.second

            // 물과 인접한 빙판 녹이기
            meltingDistance.forEach move@{
                val moveRow = row + it.first
                val moveCol = col + it.second

                if (moveRow < 0 || moveCol < 0) return@move
                if (moveRow >= R || moveCol >= C) return@move
                if (lake[moveRow][moveCol] != ICE_SHEET) return@move

                lake[moveRow][moveCol] = WATER
                meltQueue.add(Pair(moveRow, moveCol))
            }
        }

        // 녹여서 새로 생성된 물 지점
        waterQueue = meltQueue
    }

}