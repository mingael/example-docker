package org.example.algorithm.programmers.kakao_blind_2022

/**
 * 신고 결과 받기
 */
class ReportResult {

    /**
     * @param idList: 이용자 ID 목록 (중복X)
     * @param report: 이용자가 신고한 이용자 ID 목록 - 형테: 이용자id 신고한id
     * @param k: 정지 기준 신고 횟수
     *
     * @return idList 순서로 각 유저가 받은 결과 메일 수
     *
     * 테스트 1 〉	통과 (13.21ms, 61.5MB)
     * 테스트 2 〉	통과 (13.10ms, 62.1MB)
     * 테스트 3 〉	통과 (154.73ms, 143MB)
     * 테스트 4 〉	통과 (12.99ms, 62.2MB)
     * 테스트 5 〉	통과 (14.77ms, 62.3MB)
     * 테스트 6 〉	통과 (23.58ms, 64.5MB)
     * 테스트 7 〉	통과 (20.77ms, 70.6MB)
     * 테스트 8 〉	통과 (44.83ms, 84.6MB)
     * 테스트 9 〉	통과 (126.78ms, 111MB)
     * 테스트 10 〉	통과 (95.07ms, 117MB)
     * 테스트 11 〉	통과 (161.39ms, 150MB)
     * 테스트 12 〉	통과 (16.92ms, 61.9MB)
     * 테스트 13 〉	통과 (18.50ms, 62MB)
     * 테스트 14 〉	통과 (139.77ms, 119MB)
     * 테스트 15 〉	통과 (170.33ms, 148MB)
     * 테스트 16 〉	통과 (14.17ms, 62.9MB)
     * 테스트 17 〉	통과 (22.37ms, 62MB)
     * 테스트 18 〉	통과 (20.16ms, 62.7MB)
     * 테스트 19 〉	통과 (26.16ms, 62.4MB)
     * 테스트 20 〉	통과 (113.04ms, 116MB)
     * 테스트 21 〉	통과 (211.26ms, 141MB)
     * 테스트 22 〉	통과 (12.85ms, 61.2MB)
     * 테스트 23 〉	통과 (12.81ms, 62.1MB)
     * 테스트 24 〉	통과 (13.11ms, 61.2MB)
     */
    private fun run(idList: Array<String>, report: Array<String>, k: Int): IntArray {
        // 신고받은 내역 - 중복 불가
        val targetReport = mutableMapOf<String, MutableSet<String>>()
        // distinct()를 사용하면 List, Set 모두 최소 29초 걸리는데 distinct 없이 Set만 사용하면 최소 12초 걸림
        report.forEach {
            val (reporter, target) = it.split(" ")
            targetReport[target] = targetReport.getOrDefault(target, mutableSetOf())
            targetReport[target]?.add(reporter)
        }

        // 메일 내역
        val userSendMail = mutableMapOf<String, Int>()
        // 해당 유저를 신고한 모든 유저에게 정지 사실을 메일로 발송
        targetReport
            .filter { it.value.count() >= k }
            .forEach {
                it.value.forEach { userId ->
                    userSendMail[userId] = userSendMail.getOrDefault(userId, 0) + 1
                }
            }

        return idList.map { userSendMail.getOrDefault(it, 0) }.toIntArray()
    }

    /**
     * 테스트 1 〉	통과 (35.51ms, 65.4MB)
     * 테스트 2 〉	통과 (28.79ms, 65.3MB)
     * 테스트 3 〉	통과 (225.45ms, 150MB)
     * 테스트 4 〉	통과 (31.91ms, 65.8MB)
     * 테스트 5 〉	통과 (44.67ms, 65.6MB)
     * 테스트 6 〉	통과 (40.54ms, 65.4MB)
     * 테스트 7 〉	통과 (39.13ms, 72.6MB)
     * 테스트 8 〉	통과 (39.47ms, 83.5MB)
     * 테스트 9 〉	통과 (150.46ms, 131MB)
     * 테스트 10 〉	통과 (101.38ms, 113MB)
     * 테스트 11 〉	통과 (234.00ms, 179MB)
     * 테스트 12 〉	통과 (35.69ms, 67MB)
     * 테스트 13 〉	통과 (41.59ms, 65.5MB)
     * 테스트 14 〉	통과 (139.25ms, 119MB)
     * 테스트 15 〉	통과 (130.72ms, 130MB)
     * 테스트 16 〉	통과 (30.91ms, 66.7MB)
     * 테스트 17 〉	통과 (34.17ms, 66MB)
     * 테스트 18 〉	통과 (40.42ms, 66MB)
     * 테스트 19 〉	통과 (49.65ms, 66.5MB)
     * 테스트 20 〉	통과 (124.52ms, 116MB)
     * 테스트 21 〉	통과 (175.12ms, 128MB)
     * 테스트 22 〉	통과 (29.46ms, 64.8MB)
     * 테스트 23 〉	통과 (30.37ms, 65.5MB)
     * 테스트 24 〉	통과 (30.01ms, 65.4MB)
     */
    private fun run1(idList: Array<String>, report: Array<String>, k: Int): IntArray =
        report.distinct() // 중복 신고 제거
            .map { it.split(" ") }
            .groupBy { it[1] } // 유저별 신고받은 내역
            .filter { it.value.size >= k } // 신고 내역 k건 이상
            .map { a -> a.value.map { it[0] } } // 신고자 추출
            .flatten() // 그룹 해제
            .groupingBy { it }.eachCount()
            .run { idList.map { getOrDefault(it, 0) } }.toIntArray()

    fun case1() {
        val idList = arrayOf("muzi", "frodo", "apeach", "neo")
        val report = arrayOf("muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi")
        val k = 2
        val result = run1(idList, report, k)
        print("case1: ")
        result.forEach { print("$it ") }
        println()
    }

    fun case2() {
        val idList = arrayOf("con", "ryan")
        val report = arrayOf("ryan con", "ryan con", "ryan con", "ryan con")
        val k = 3
        val result = run(idList, report, k)
        print("case2: ")
        result.forEach { print("$it ") }
        println()
    }

}