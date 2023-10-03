package org.example.algorithm.programmers.kakao_tech_internship_2022

/**
 * 성격 유형 검사하기
 */
class CheckPersonalityTypes {
    // 선택지별 점수
    private val choiceScore = intArrayOf(3, 2, 1, 0, 1, 2, 3)
    // 유형 점수
    private val typeScore = mutableMapOf(
        'R' to 0, 'T' to 0,
        'C' to 0, 'F' to 0,
        'J' to 0, 'M' to 0,
        'A' to 0, 'N' to 0
    )

    private fun solution(survey: Array<String>, choices: IntArray): String {
        for (index in survey.indices) {
            val choice = choices[index]
            // 선택지 '모르겠음'은 건너뛰기
            if (choice == 4) continue
            // 선택지에 따른 점수 부여
            val types = survey[index]
            val type = if (choice < 4) types[0] else types[1]
            typeScore[type] = choiceScore[choice - 1] + typeScore.getOrDefault(type, 0)
        }

        // 점수가 같으면, 두 성격 유형 중 사전 순으로 빠른 성격 유형을 선택
        var answer = if (typeScore.getOrDefault('R', 0) >= typeScore.getOrDefault('T', 0)) "R" else "T"
        answer += if (typeScore.getOrDefault('C', 0) >= typeScore.getOrDefault('F', 0)) "C" else "F"
        answer += if (typeScore.getOrDefault('J', 0) >= typeScore.getOrDefault('M', 0)) "J" else "M"
        answer += if (typeScore.getOrDefault('A', 0) >= typeScore.getOrDefault('N', 0)) "A" else "N"
        return answer
    }

    fun case1() {
        val result = solution(arrayOf("AN", "CF", "MJ", "RT", "NA"), intArrayOf(5, 3, 2, 7, 5))
        println("case1 result: $result, ${result == "TCMA"}")
    }

    fun case2() {
        val result = solution(arrayOf("TR", "RT", "TR"), intArrayOf(7, 1, 3))
        println("case2 result: $result, ${result == "RCJA"}")
    }

}