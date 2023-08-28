package org.example.baekjoon.redbin0471

import java.util.*

class SwansLake {

    companion object {
        private const val WATER = '.'
        private const val ICE_SHEET = 'X'
        private const val SWAN = 'L'
    }

    private var row: Int = 0
    private var col: Int = 0

    private lateinit var lake: Array<Array<Char>>

    // 빙판 녹는 거리
    private val meltingDistance = arrayOf(Pair(-1, 0), Pair(0, -1), Pair(1, 0), Pair(0, 1))

    fun example1() {
        init()

        println("전")
        lake.forEach { arr -> arr.forEach { print(it) }; println() }


        (1..3).forEach { day ->
            meltingTheLake()
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
                row = nextToken().toInt()
                col = nextToken().toInt()
            }

            // 호수 초기 상태
            lake = Array(row) { _ ->
                val str = StringTokenizer(readln()).nextToken()
                Array(col) { str[it] }
            }
        }
    }

    private fun moveSwan() {
        // TODO()
    }

    /**
     * 빙판 녹이기
     */
    private fun meltingTheLake() {
        val meltLake = Array(row) { Array(col) { ICE_SHEET } }
        lake.forEachIndexed { r, rowCol ->
            rowCol.forEachIndexed colReturn@{ c, value ->
                if (value == ICE_SHEET) return@colReturn

                meltLake[r][c] = value

                if (value == WATER) {
                    meltingDistance.forEach move@{
                        val moveRow = r + it.first
                        val moveCol = c + it.second
                        if (moveRow < 0 || moveCol < 0) return@move
                        if (moveRow >= row || moveCol >= col) return@move
                        if (lake[moveRow][moveCol] == ICE_SHEET) meltLake[moveRow][moveCol] = WATER
                    }
                }
            }
        }
        lake = meltLake
    }

}