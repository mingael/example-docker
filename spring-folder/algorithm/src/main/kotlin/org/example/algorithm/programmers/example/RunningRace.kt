package org.example.algorithm.programmers.example

/**
 * 호명된 선수가 마지막에 있을 수 있기 때무에 찾는 시간을 줄여야 한다.
 * - 배열은 시간 복잡도가 O(n)으로 시간 초과 발생
 * - 해시를 사용하면 시간 복잡도가 O(1)
 */
class RunningRace {

    /**
     * 테스트 1 〉	통과 (0.08ms, 62.4MB)
     * 테스트 2 〉	통과 (0.09ms, 61.4MB)
     * 테스트 3 〉	통과 (0.31ms, 61.3MB)
     * 테스트 4 〉	통과 (1.43ms, 60.7MB)
     * 테스트 5 〉	통과 (5.41ms, 69.9MB)
     * 테스트 6 〉	통과 (15.38ms, 82.8MB)
     * 테스트 7 〉	통과 (81.07ms, 98.4MB)
     * 테스트 8 〉	통과 (88.18ms, 103MB)
     * 테스트 9 〉	통과 (170.64ms, 122MB)
     * 테스트 10 〉	통과 (380.62ms, 253MB)
     * 테스트 11 〉	통과 (732.31ms, 368MB)
     * 테스트 12 〉	통과 (344.46ms, 339MB)
     * 테스트 13 〉	통과 (517.83ms, 361MB)
     * 테스트 14 〉	통과 (0.10ms, 61.7MB)
     * 테스트 15 〉	통과 (0.09ms, 62.4MB)
     * 테스트 16 〉	통과 (0.09ms, 62.9MB)
     */
    fun run(players: Array<String>, callings: Array<String>): Array<String> {
        val rank = hashMapOf<String, Int>()
        val playersMap = hashMapOf<Int, String>()
        for (i in players.indices) {
            playersMap[i] = players[i]
            rank[players[i]] = i
        }

        callings.forEach {
            val index = rank.getOrDefault(it, 0)
            val front = playersMap[index - 1]!!

            playersMap[index - 1] = it
            playersMap[index] = front

            rank[it] = index - 1
            rank[front] = index
        }

        val answer = Array(players.size) { "" }
        playersMap.forEach { answer[it.key] = it.value }
        return answer
    }

    /**
     * 시간 복잡도가 O(n)으로 시간 초과 발생
     *
     * 테스트 1 〉	통과 (18.47ms, 61.6MB)
     * 테스트 2 〉	통과 (17.90ms, 62.5MB)
     * 테스트 3 〉	통과 (14.77ms, 62.8MB)
     * 테스트 4 〉	통과 (26.08ms, 62.7MB)
     * 테스트 5 〉	통과 (24.51ms, 70.5MB)
     * 테스트 6 〉	통과 (40.61ms, 83.5MB)
     * 테스트 7 〉	통과 (463.39ms, 97MB)
     * 테스트 8 〉	통과 (1914.85ms, 100MB)
     * 테스트 9 〉	통과 (9973.86ms, 123MB)
     * 테스트 10 〉	실패 (시간 초과)
     * 테스트 11 〉	실패 (시간 초과)
     * 테스트 12 〉	실패 (시간 초과)
     * 테스트 13 〉	실패 (시간 초과)
     * 테스트 14 〉	통과 (15.54ms, 62.9MB)
     * 테스트 15 〉	통과 (15.28ms, 62.3MB)
     * 테스트 16 〉	통과 (14.04ms, 62.6MB)
     */
    fun run1(players: Array<String>, callings: Array<String>): Array<String> {
        for (i in callings.indices) {
            val index = players.indexOf(callings[i])
            val front = players[index - 1]
            players[index - 1] = players[index]
            players[index] = front
        }
        return players
    }


    /**
     * 시간 복잡도가 O(n)으로 시간 초과 발생
     *
     * 테스트 1 〉	통과 (31.24ms, 62.8MB)
     * 테스트 2 〉	통과 (22.72ms, 63.3MB)
     * 테스트 3 〉	통과 (18.91ms, 64.4MB)
     * 테스트 4 〉	통과 (21.51ms, 64.6MB)
     * 테스트 5 〉	통과 (31.95ms, 66.3MB)
     * 테스트 6 〉	통과 (48.98ms, 81.6MB)
     * 테스트 7 〉	통과 (632.18ms, 97.3MB)
     * 테스트 8 〉	통과 (2599.56ms, 101MB)
     * 테스트 9 〉	실패 (시간 초과)
     * 테스트 10 〉	실패 (시간 초과)
     * 테스트 11 〉	실패 (시간 초과)
     * 테스트 12 〉	실패 (시간 초과)
     * 테스트 13 〉	실패 (시간 초과)
     * 테스트 14 〉	통과 (22.42ms, 64.3MB)
     * 테스트 15 〉	통과 (18.66ms, 63.6MB)
     * 테스트 16 〉	통과 (23.34ms, 63.4MB)
     */
    fun run1List(players: Array<String>, callings: Array<String>): Array<String> {
        val answer = players.toMutableList()
        for (i in callings.indices) {
            val index = answer.indexOf(callings[i]) // O(n)
            answer.removeAt(index) // O(n)
            answer.add(index - 1, callings[i]) // O(1)
        }
        return answer.toTypedArray()
    }

    /**
     * 테스트 1 〉	통과 (3.25ms, 61.6MB)
     * 테스트 2 〉	통과 (2.78ms, 64MB)
     * 테스트 3 〉	통과 (3.64ms, 60.9MB)
     * 테스트 4 〉	통과 (3.63ms, 61.1MB)
     * 테스트 5 〉	통과 (7.02ms, 72.8MB)
     * 테스트 6 〉	통과 (13.57ms, 88.5MB)
     * 테스트 7 〉	통과 (37.90ms, 97.2MB)
     * 테스트 8 〉	통과 (50.42ms, 109MB)
     * 테스트 9 〉	통과 (131.37ms, 124MB)
     * 테스트 10 〉	통과 (242.71ms, 230MB)
     * 테스트 11 〉	통과 (352.97ms, 365MB)
     * 테스트 12 〉	통과 (323.21ms, 340MB)
     * 테스트 13 〉	통과 (397.47ms, 359MB)
     * 테스트 14 〉	통과 (2.90ms, 62.1MB)
     * 테스트 15 〉	통과 (5.20ms, 61MB)
     * 테스트 16 〉	통과 (4.10ms, 61.3MB)
     */
    fun run2(players: Array<String>, callings: Array<String>): Array<String> {
        val rank = players.mapIndexed { idx, name -> name to idx }.toMap().toMutableMap()
        // callings.forEach, callings.forEachIndexed 로 바꿔도 속도 차이 없음
        callings.forEach {
            val index = rank.getOrDefault(it, 0)
            // players를 Map으로 변환해서 사용해도 속도 차이 없음
            val front = players[index - 1]

            players[index - 1] = it
            players[index] = front

            rank[it] = index - 1
            rank[front] = index
        }
        return players
    }

    /**
     * run2와 차이 없음
     * 선수 호출을 좀 더 간단하게 바꿔봤지만 속도 차이 없음
     *
     * 테스트 1 〉	통과 (3.82ms, 61.5MB)
     * 테스트 2 〉	통과 (3.36ms, 60.8MB)
     * 테스트 3 〉	통과 (4.31ms, 61.8MB)
     * 테스트 4 〉	통과 (5.20ms, 61MB)
     * 테스트 5 〉	통과 (9.30ms, 74.2MB)
     * 테스트 6 〉	통과 (12.17ms, 85.5MB)
     * 테스트 7 〉	통과 (52.98ms, 94.9MB)
     * 테스트 8 〉	통과 (92.23ms, 104MB)
     * 테스트 9 〉	통과 (178.04ms, 123MB)
     * 테스트 10 〉	통과 (224.37ms, 228MB)
     * 테스트 11 〉	통과 (387.73ms, 359MB)
     * 테스트 12 〉	통과 (337.11ms, 342MB)
     * 테스트 13 〉	통과 (351.00ms, 355MB)
     * 테스트 14 〉	통과 (2.74ms, 61.9MB)
     * 테스트 15 〉	통과 (2.92ms, 61.9MB)
     * 테스트 16 〉	통과 (3.11ms, 62.4MB)
     */
    fun run3(players: Array<String>, callings: Array<String>): Array<String> {
        val rank = players.mapIndexed { idx, name -> name to idx }.toMap().toMutableMap()
        callings.forEachIndexed { _, player ->
            val index = rank.getOrDefault(player, 0)

            val front = players[index - 1]
            players[index - 1] = player
            players[index] = front

            rank[player] = index - 1
            rank[front] = index
        }
        return players
    }

    /**
     * run2와 차이 없음
     */
    fun run4(players: Array<String>, callings: Array<String>): Array<String> {
        val rank = players.mapIndexed { idx, name -> name to idx }.toMap().toMutableMap()
        val answer = players.mapIndexed { idx, name -> idx to name }.toMap().toMutableMap()

        callings.forEachIndexed { _, player ->
            val index = rank.getOrDefault(player, 0)
            val front = answer[index - 1]!!

            answer[index - 1] = player
            answer[index] = front

            rank[player] = index - 1
            rank[front] = index
        }
        return answer.toSortedMap().map { it.value }.toTypedArray()
    }

    /**
     * run2와 차이 없음
     *
     * 테스트 1 〉	통과 (2.28ms, 61.8MB)
     * 테스트 2 〉	통과 (2.34ms, 61.8MB)
     * 테스트 3 〉	통과 (3.38ms, 61.5MB)
     * 테스트 4 〉	통과 (3.85ms, 61.4MB)
     * 테스트 5 〉	통과 (8.69ms, 69.8MB)
     * 테스트 6 〉	통과 (16.68ms, 83.3MB)
     * 테스트 7 〉	통과 (59.81ms, 98.7MB)
     * 테스트 8 〉	통과 (87.70ms, 110MB)
     * 테스트 9 〉	통과 (238.57ms, 114MB)
     * 테스트 10 〉	통과 (574.22ms, 251MB)
     * 테스트 11 〉	통과 (829.28ms, 364MB)
     * 테스트 12 〉	통과 (618.02ms, 367MB)
     * 테스트 13 〉	통과 (428.99ms, 339MB)
     * 테스트 14 〉	통과 (3.29ms, 60.7MB)
     * 테스트 15 〉	통과 (2.23ms, 62.1MB)
     * 테스트 16 〉	통과 (2.45ms, 61MB)
     */
    fun run5(players: Array<String>, callings: Array<String>): Array<String> {
        val rank = hashMapOf<String, Int>()
        val playersMap = hashMapOf<Int, String>()
        for (i in players.indices) {
            rank[players[i]] = i
            playersMap[i] = players[i]
        }

        for (i in callings.indices) {
            val player = callings[i]
            val index = rank.getOrDefault(player, 0)
            val front = playersMap[index - 1]!!

            playersMap[index - 1] = player
            playersMap[index] = front

            rank[player] = index - 1
            rank[front] = index
        }
        return playersMap.toSortedMap().map { it.value }.toTypedArray()
    }

    /**
     * 스트
     * 테스트 1 〉	통과 (1.55ms, 60.1MB)
     * 테스트 2 〉	통과 (1.06ms, 63MB)
     * 테스트 3 〉	통과 (1.64ms, 61.2MB)
     * 테스트 4 〉	통과 (2.70ms, 60.4MB)
     * 테스트 5 〉	통과 (7.35ms, 70.4MB)
     * 테스트 6 〉	통과 (19.66ms, 82.3MB)
     * 테스트 7 〉	통과 (51.13ms, 96.3MB)
     * 테스트 8 〉	통과 (74.32ms, 111MB)
     * 테스트 9 〉	통과 (143.95ms, 120MB)
     * 테스트 10 〉	통과 (255.26ms, 254MB)
     * 테스트 11 〉	통과 (590.54ms, 363MB)
     * 테스트 12 〉	통과 (637.80ms, 359MB)
     * 테스트 13 〉	통과 (586.92ms, 373MB)
     * 테스트 14 〉	통과 (1.24ms, 61.7MB)
     * 테스트 15 〉	통과 (1.13ms, 59.7MB)
     * 테스트 16 〉	통과 (1.64ms, 61.3MB)
     */
    fun run6(players: Array<String>, callings: Array<String>): Array<String> {
        val rank = hashMapOf<String, Int>()
        val playersMap = hashMapOf<Int, String>()
        for (i in players.indices) {
            rank[players[i]] = i
            playersMap[i] = players[i]
        }

        for (i in callings.indices) {
            val player = callings[i]
            val index = rank.getOrDefault(player, 0)
            val front = playersMap[index - 1]!!

            playersMap[index - 1] = player
            playersMap[index] = front

            rank[player] = index - 1
            rank[front] = index
        }

        val answer = Array(players.size) { "" }
        playersMap.forEach { k, v -> answer[k] = v }
        return answer
    }

    fun case1() {
        val result = run(arrayOf("mumu", "soe", "poe", "kai", "mine"), arrayOf("kai", "kai", "mine", "mine"))
        println("[${result.joinToString(", ")}]")
    }
}
