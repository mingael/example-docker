package org.example.algorithm.programmers.introduction

class Parallel {
    private fun run(dots: Array<IntArray>): Int {
        // 평행이 존재하면 1, 존재하지 않으면 0
        var answer = 0

        // 기울기 기록 - key: 기울기, value: 점1 좌표, 점2 좌표
        val slopes = mutableMapOf<Double, Pair<IntArray, IntArray>>()

        // 동일한 점끼리 비교하지 않도록 반복문 설정
        for (i in 0 until dots.size - 1) {
            if (answer == 1) break
            // 이미 계산된 i번째 좌표는 제외한다.
            for (j in i + 1 until dots.size) {
                // 기울기 = y값 증가량 / x값 증가량
                val x = dots[i][0] - dots[j][0]
                val y = dots[i][1] - dots[j][1]
                val slope = y.toDouble() / x.toDouble()

                // 동일한 기울기가 없으면 기록
                if (!slopes.containsKey(slope)) slopes[slope] = Pair(dots[i], dots[j])
                else {
                    slopes.filter { it.key == slope }
                        .forEach {
                            // 각 점들이 연결되는 경우 제외
                            if (dots[i][0] == it.value.first[0] || dots[i][1] == it.value.first[1]) return@forEach
                            if (dots[i][0] == it.value.second[0] || dots[i][1] == it.value.second[1]) return@forEach
                            if (dots[j][0] == it.value.second[0] || dots[j][1] == it.value.second[1]) return@forEach
                            if (dots[j][0] == it.value.first[0] || dots[j][1] == it.value.first[1]) return@forEach
                            answer = 1
                        }
                }
            }
        }

        return answer
    }

    fun case1() {
        val case = arrayOf(intArrayOf(1, 4), intArrayOf(9, 2), intArrayOf(3, 8), intArrayOf(11, 6))
        val result = Parallel().run(case)
        println("case1: $result, ${result == 1}")
    }

    fun case2() {
        val case = arrayOf(intArrayOf(3, 5), intArrayOf(4, 1), intArrayOf(2, 4), intArrayOf(5, 10))
        val result = Parallel().run(case)
        println("case2: $result, ${result == 0}")
    }

    fun case3() {
        val case = arrayOf(intArrayOf(1, 1), intArrayOf(2, 2), intArrayOf(3, 3), intArrayOf(50, 100))
        val result = Parallel().run(case)
        println("case3: $result, ${result == 0}")
    }

    fun case4() {
        val case = arrayOf(intArrayOf(1, 2), intArrayOf(5, 1), intArrayOf(3, 6), intArrayOf(6, 3))
        val result = Parallel().run(case)
        println("case4: $result, ${result == 1}")
    }

    fun case5() {
        val case = arrayOf(intArrayOf(1, 2), intArrayOf(2, 1), intArrayOf(3, 4), intArrayOf(4, 5))
        val result = Parallel().run(case)
        println("case5: $result, ${result == 0}")
    }

    fun case6() {
        val case = arrayOf(intArrayOf(1, 1), intArrayOf(4, 2), intArrayOf(5, 5), intArrayOf(7, 7))
        val result = Parallel().run(case)
        println("case6: $result, ${result == 0}")
    }

}