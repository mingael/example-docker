package org.example.algorithm.baekjoon.redbin0471

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.math.max

/**
 * 평범한 배낭
 */
class KnapsackProblem {

    /**
     * 각 물건에 대해 해당 물건의 무게보다 크거나 같은 경우에 대해서만 DP 갱신
     * memory: 14380 KB
     * time: 176 ms
     */
    fun example3() {
        System.`in`.bufferedReader().run {
            StringTokenizer(readln()).run {
                val n = nextToken().toInt()
                val k = nextToken().toInt()

                val dp = IntArray(k + 1)
                val products = Array(n) {
                    StringTokenizer(readln()).let { Pair(it.nextToken().toInt(), it.nextToken().toInt()) }
                }
                products.forEach { product ->
                    // 범위: 대상 물건 무게 <= 현재 허용 무게 <= 최대 무게
                    // 이전 값을 사용해야하는데 오름차순으로 처리하면 값이 갱신되기 때문에 내림차순으로 계산
                    (k downTo product.first).forEach { i ->
                        dp[i] = max(dp[i], dp[i - product.first] + product.second)
                    }
                    dpPrint(dp)
                }

                println("결과: ${dp[k]}")
            }
        }
    }

    /**
     * import 필요 없음
     * memory: 57616 KB
     * title: 372 ms
     */
    fun example2() {
        val (n, k) = readln().split(" ").map { it.toInt() }
        val dp = Array(n + 1) { IntArray(k + 1) { 0 } }
        (1..n).forEach { index ->
            val (w, v) = readln().split(" ").map { it.toInt() }
            (1..k).forEach { maxWeight ->
                dp[index][maxWeight] = dp[index - 1][maxWeight]
                if (w <= maxWeight)
                    dp[index][maxWeight] = dp[index][maxWeight].coerceAtLeast(dp[index - 1][maxWeight - w] + v)
            }
        }

        dpPrint(dp)

        println("결과: ${dp[n][k]}")
    }

    /**
     * memory: 53112 KB
     * time: 272 ms
     */
    fun example1() {
        print("입력 1. 물품 수, 버틸 수 있는 무게: ")
        val bufferReader = BufferedReader(InputStreamReader(System.`in`))
        val input = StringTokenizer(bufferReader.readLine())
        val number = input.nextToken().toInt()
        val maxWeight = input.nextToken().toInt()

        println("입력 2. 물품 수만큼 무게와 가치를 입력해주세요.")
        val products = mutableListOf<Pair<Int, Int>>()
        repeat(number) {
            val productInput = StringTokenizer(bufferReader.readLine())
            products.add(Pair(productInput.nextToken().toInt(), productInput.nextToken().toInt()))
        }

        val result = knapsack(maxWeight, number, products)
        println("결과: $result")
    }


    /**
     * 최대 가치 구하기
     *
     * @param maxWeight: 최대 무게
     * @param products: 물건
     * @param number: 물건 수
     */
    private fun knapsack(maxWeight: Int, number: Int, products: List<Pair<Int, Int>>): Int {
        val dp = Array(number + 1) { IntArray(maxWeight + 1) { 0 } }

        // 물건 index
        for (index in 1..number) {
            // 허용 무게
            for (w in 1..maxWeight) {
                val (weight, value) = products[index - 1]
                // 대상 물건의 무게가 현재 허용 무게보다 작으면
                if (weight <= w)
                // 이전 가치와 '대상 물건 가치 + 남는 무게에 들어올 수 있는 이전 가치' 중 최대값을 구해서 적용
                    dp[index][w] = max(dp[index - 1][w], value + dp[index - 1][w - weight])
                // 대상 물건의 무게가 현재 허용 무게보다 크면 이전 가치를 그대로 적용
                else dp[index][w] = dp[index - 1][w]
            }
        }

        dpPrint(dp)

        return dp[number][maxWeight]
    }

    private fun dpPrint(dp: Array<IntArray>) {
        println("DP: [")
        dp.forEach { arr -> arr.forEach { print("$it ") }; println() }
        println("]")
    }

    private fun dpPrint(dp: IntArray) {
        print("DP: [ ")
        dp.forEach { print("$it ") }
        println("]")
    }

}
