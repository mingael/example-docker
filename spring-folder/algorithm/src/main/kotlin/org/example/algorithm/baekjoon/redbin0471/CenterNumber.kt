package org.example.algorithm.baekjoon.redbin0471

import java.util.*

class CenterNumber {

    /**
     * 출력값을 매번 println으로 출력하면 시간 초과 발생하기 때문에 StringBuilder를 사용한다.
     *
     * memory: 47984 KB
     * time: 528 ms
     */
    fun example1() {
        val right = PriorityQueue<Int>() // 오름차순
        val left = PriorityQueue<Int>(Collections.reverseOrder())    // 내림차순
        val sb = StringBuilder()

        System.`in`.bufferedReader().run {
            StringTokenizer(readln()).run {
                repeat(nextToken().toInt()) {
                    val n = readln().toInt()
                    if (left.size == right.size) left.add(n) else right.add(n)
                    if (right.isNotEmpty() && left.peek() > right.peek()) {
                        left.add(right.poll())
                        right.add(left.poll())
                    }
                    sb.append("${left.peek()} \n")
                }
            }
        }
        println(sb)
    }

}