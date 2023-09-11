package org.example.algorithm.programmers.kakao_blind_2023

class PersonalInformationCollectionValidityPeriod {

    companion object {
        private const val DAYS_OF_MONTH = 28
    }

    /**
     * 테스트 1 〉	통과 (16.51ms, 63.8MB)
     * 테스트 2 〉	통과 (18.48ms, 63.8MB)
     * 테스트 3 〉	통과 (16.08ms, 64.4MB)
     * 테스트 4 〉	통과 (15.41ms, 64.1MB)
     * 테스트 5 〉	통과 (28.64ms, 63MB)
     * 테스트 6 〉	통과 (24.48ms, 63.2MB)
     * 테스트 7 〉	통과 (28.17ms, 63MB)
     * 테스트 8 〉	통과 (26.87ms, 64.5MB)
     * 테스트 9 〉	통과 (22.01ms, 63.6MB)
     * 테스트 10 〉	통과 (18.40ms, 63.4MB)
     * 테스트 11 〉	통과 (16.54ms, 64.2MB)
     * 테스트 12 〉	통과 (24.17ms, 63.5MB)
     * 테스트 13 〉	통과 (23.84ms, 63.5MB)
     * 테스트 14 〉	통과 (18.27ms, 63.7MB)
     * 테스트 15 〉	통과 (24.19ms, 64MB)
     * 테스트 16 〉	통과 (21.19ms, 64.7MB)
     * 테스트 17 〉	통과 (16.92ms, 63.9MB)
     * 테스트 18 〉	통과 (17.29ms, 64.6MB)
     * 테스트 19 〉	통과 (17.57ms, 64.2MB)
     * 테스트 20 〉	통과 (16.94ms, 64.1MB)
     */
    fun run(today: String, terms: Array<String>, privacies: Array<String>): IntArray {
        // 오늘 날짜 일수
        val todayOfDays = convertDateOfNumberOfDays(today)

        // 약관별 유효기간
        val termMap = hashMapOf<String, Int>()
        terms.forEach {
            val tmp = it.split(" ")
            termMap[tmp[0]] = tmp[1].toInt()
        }

        var answer = intArrayOf()
        for (i in privacies.indices) {
            val tmp = privacies[i].split(" ")
            val validityPeriod = convertDateOfNumberOfDays(tmp[0]) + termMap[tmp[1]]!! * DAYS_OF_MONTH
            // 유효기간 지나면 파기 대상
            if (todayOfDays > validityPeriod) answer += i + 1
        }
        return answer
    }

    /**
     * 날짜를 일수로 변환
     */
    private fun convertDateOfNumberOfDays(day: String): Int {
        val tmp = day.split(".")
        return tmp[2].toInt() + (tmp[1].toInt() - 1) * DAYS_OF_MONTH + tmp[0].toInt() * 12 * DAYS_OF_MONTH
    }

    fun case1() {
        val result = run(
            today = "2022.05.19",
            terms = arrayOf("A 6", "B 12", "C 3"),
            privacies = arrayOf("2021.05.02 A", "2021.07.01 B", "2022.02.19 C", "2022.02.20 C")
        )
        println("case 1 : ${result.joinToString(",")}")
    }

    fun case2() {
        val result = run(
            today = "2020.01.01",
            terms = arrayOf("Z 3", "D 5"),
            privacies = arrayOf("2019.01.01 D", "2019.11.15 Z", "2019.08.02 D", "2019.07.01 D", "2018.12.28 Z")
        )
        println("case 1 : ${result.joinToString(",")}")
    }
}