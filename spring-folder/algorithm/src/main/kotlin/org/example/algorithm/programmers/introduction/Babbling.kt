package org.example.algorithm.programmers.introduction

/**
 * 옹알이 1
 */
class Babbling {

    /**
     * 테스트 1 〉	통과 (40.90ms, 64.6MB)
     * 테스트 2 〉	통과 (22.53ms, 65MB)
     * 테스트 3 〉	통과 (27.24ms, 64.7MB)
     * 테스트 4 〉	통과 (24.95ms, 65.4MB)
     * 테스트 5 〉	통과 (26.33ms, 65.2MB)
     * 테스트 6 〉	통과 (32.73ms, 64MB)
     * 테스트 7 〉	통과 (34.32ms, 64.5MB)
     * 테스트 8 〉	통과 (33.55ms, 64.1MB)
     * 테스트 9 〉	통과 (33.85ms, 64.7MB)
     * 테스트 10 〉	통과 (25.76ms, 65.2MB)
     * 테스트 11 〉	통과 (31.77ms, 64.7MB)
     * 테스트 12 〉	통과 (24.11ms, 64.5MB)
     * 테스트 13 〉	통과 (32.54ms, 65.1MB)
     * 테스트 14 〉	통과 (34.30ms, 63.8MB)
     * 테스트 15 〉	통과 (25.05ms, 64.7MB)
     * 테스트 16 〉	통과 (25.60ms, 65.2MB)
     * 테스트 17 〉	통과 (26.95ms, 64.6MB)
     */
    fun run(babbling: Array<String>): Int {
        var answer = 0
        val keyword = listOf("aya", "ye", "woo", "ma")
        babbling.forEach { str ->
            var tmp = str
            keyword.forEach {
                tmp = tmp.replace(it, " ")
            }
            if (tmp.trim().isEmpty()) answer++
        }
        return answer
    }

    /**
     * 테스트 1 〉	통과 (2.32ms, 59.6MB)
     * 테스트 2 〉	통과 (3.49ms, 61.5MB)
     * 테스트 3 〉	통과 (1.91ms, 61.8MB)
     * 테스트 4 〉	통과 (3.20ms, 59.4MB)
     * 테스트 5 〉	통과 (2.83ms, 59.9MB)
     * 테스트 6 〉	통과 (1.84ms, 59.4MB)
     * 테스트 7 〉	통과 (2.69ms, 58.9MB)
     * 테스트 8 〉	통과 (2.20ms, 61MB)
     * 테스트 9 〉	통과 (2.41ms, 59.9MB)
     * 테스트 10 〉	통과 (1.48ms, 61MB)
     * 테스트 11 〉	통과 (1.29ms, 61.5MB)
     * 테스트 12 〉	통과 (1.25ms, 60.8MB)
     * 테스트 13 〉	통과 (1.86ms, 58.9MB)
     * 테스트 14 〉	통과 (1.31ms, 62.1MB)
     * 테스트 15 〉	통과 (1.30ms, 62.2MB)
     * 테스트 16 〉	통과 (1.37ms, 60.4MB)
     * 테스트 17 〉	통과 (1.36ms, 61.2MB)
     */
    fun run1(babbling: Array<String>): Int {
        var answer = 0
        val regex = "aya|ye|woo|ma".toRegex()
        babbling.forEach {
            val tmp = it.replace(regex, "")
            if (tmp.isEmpty()) answer++
        }
        return answer
    }

    fun case1() {
        val babbling = arrayOf("aya", "yee", "u", "maa", "wyeoo")
        val result = run(babbling)
        println("case1: $result, ${result == 1}")
    }

    fun case2() {
        val babbling = arrayOf("ayaye", "uuuma", "ye", "yemawoo", "ayaa")
        val result = run(babbling)
        println("case2: $result, ${result == 3}")
    }

}