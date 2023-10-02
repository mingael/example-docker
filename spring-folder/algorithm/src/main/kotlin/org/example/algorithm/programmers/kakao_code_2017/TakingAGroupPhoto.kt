package org.example.algorithm.programmers.kakao_code_2017

import kotlin.math.abs

/**
 * 단체사진 찍기
 * 
 * 대상 8개를 정렬하는 방법으로 조건이 최대 100개 주어지므로 순열(Permutation) 문제이다.
 * 순열은 주어진 요소를 순서에 따라 배열하는 방법의 경우의 수를 나타낸다.
 */
class TakingAGroupPhoto {

    // 입력 조건수가 최대 100이고, 대상이 8개로 모든 경우의 수가 8!이다.
    // 깊이 우선 탐색(DFS, Depth-First Search)
    // 모든 경우의 수를 구하여 조건을 만족하는 경우 구하기
    private val friends = "ACFJMNRT"
    private var answer = 0

    private fun solution(n: Int, data: Array<String>): Int {
        permutation(
            depth = 0,
            alignedFriends = Array(friends.length) { '_' },
            visited = Array(friends.length) { false },
            conditions = data
        )
        return answer
    }

    /**
     * 순열
     * - 서로 다른 n개의 원소에서 r개를 중복없이 골라 순서에 상관있게 나열한다.
     *
     * @param depth: 깊이
     * @param alignedFriends: 정렬된 친구들
     * @param visited: 방문 여부
     * @param conditions:  조건
     */
    private fun permutation(
        depth: Int,
        alignedFriends: Array<Char>,
        visited: Array<Boolean>,
        conditions: Array<String>
    ) {
        // 순열 처리가 끝나면 조건을 만족하는지 확인
        if (depth == friends.length) {
            if (check(conditions, alignedFriends)) answer++
            return
        }
        // 순열 처리
        for (i in friends.indices) {
            if (visited[i]) continue

            alignedFriends[depth] = friends[i]
            visited[i] = true
            permutation(depth + 1, alignedFriends, visited, conditions)
            visited[i] = false
        }
    }

    /**
     * 조건을 만족하는 지 확인
     */
    private fun check(conditions: Array<String>, alignedFriends: Array<Char>): Boolean {
        conditions.forEach { condition ->
            val from = alignedFriends.indexOf(condition[0])
            val to = alignedFriends.indexOf(condition[2])
            val diff = abs(from - to)
            val interval = condition[4].toString().toInt() + 1
            when (condition[3]) {
                '>' -> if (diff <= interval) return false
                '<' -> if (diff >= interval) return false
                '=' -> if (diff != interval) return false
            }
        }
        return true
    }

    fun case1() {
        val result = solution(2, arrayOf("N~F=0", "R~T>2"))
        println("case1: $result, ${result == 3648}")
    }

    fun case2() {
        val result = solution(2, arrayOf("M~C<2", "C~M>1"))
        println("case2: $result, ${result == 0}")
    }
}