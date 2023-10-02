package org.example.algorithm.programmers.kakao_blind_2023

/**
 * Q: https://school.programmers.co.kr/learn/courses/30/lessons/150369
 * A: https://school.programmers.co.kr/questions/43364
 */
class DeliveryAndCollectionOfParcels {

    private fun solution(cap: Int, n: Int, deliveries: IntArray, pickups: IntArray): Long {
        var answer: Long = 0

        var delivery = 0 // 남은 택배 상자
        var pickup = 0 // 남은 수거 상자

        // 무조건 왕복해야하기 때문에 가장 멀리 있는 집부터 거꾸로 방문
        for (i in n - 1 downTo 0) {
            // 배달, 수거 처리해야 하는 양
            delivery += deliveries[i]
            pickup += pickups[i]
            // 배달 및 수거를 전부 처리할 때까지 왕복
            while (delivery > 0 || pickup > 0) {
                delivery -= cap
                pickup -= cap
                // i+1번째 집과 물류창고를 왕복
                answer += (i + 1) * 2
            }
        }

        return answer
    }

    fun case1() {
        val result = solution(4, 5, intArrayOf(1, 0, 3, 1, 2), intArrayOf(0, 3, 0, 4, 0))
        println("case1: $result, ${result == 16L}")
    }

    fun case2() {
        val result = solution(2, 7, intArrayOf(1, 0, 2, 0, 1, 0, 2), intArrayOf(0, 2, 0, 1, 0, 2, 0))
        println("case2: $result, ${result == 30L}")
    }

}