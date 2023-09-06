package org.example.algorithm.programmers.example

/**
 * 공원 산책
 */
class WalkInThePark {

    companion object {
        private const val START = 'S'
        private const val BLOCK = 'X'
        private const val ROAD = 'O'
    }

    /**
     * 테스트 1 〉	통과 (32.82ms, 63.3MB)
     * 테스트 2 〉	통과 (21.73ms, 63.4MB)
     * 테스트 3 〉	통과 (21.69ms, 63.8MB)
     * 테스트 4 〉	통과 (29.60ms, 64MB)
     * 테스트 5 〉	통과 (26.18ms, 64.4MB)
     * 테스트 6 〉	통과 (32.12ms, 64.8MB)
     * 테스트 7 〉	통과 (22.05ms, 64.1MB)
     * 테스트 8 〉	통과 (22.59ms, 64.2MB)
     * 테스트 9 〉	통과 (32.01ms, 64.2MB)
     * 테스트 10 〉	통과 (21.94ms, 64.3MB)
     * 테스트 11 〉	통과 (25.77ms, 64.6MB)
     * 테스트 12 〉	통과 (32.53ms, 64.3MB)
     * 테스트 13 〉	통과 (22.58ms, 64.2MB)
     * 테스트 14 〉	통과 (24.18ms, 64.1MB)
     * 테스트 15 〉	통과 (21.78ms, 64.6MB)
     * 테스트 16 〉	통과 (23.61ms, 64.3MB)
     * 테스트 17 〉	통과 (21.59ms, 64.1MB)
     * 테스트 18 〉	통과 (27.57ms, 63.9MB)
     * 테스트 19 〉	통과 (22.33ms, 63.9MB)
     * 테스트 20 〉	통과 (22.08ms, 64.1MB)
     */
    private fun run(park: Array<String>, routes: Array<String>): IntArray {
        // 로봇 위치
        var robot = Pair(0, 0)

        // 공원 바둑판 그리기
        val row = park.first().length
        val col = park.count()
        val board = Array(row) { Array(col) { ROAD } }
        park.forEachIndexed { y, str ->
            for (x in str.indices) {
                board[x][y] = str[x]
                if (str[x] == START) robot = Pair(x, y)
            }
        }

        // 로봇 강아지 이동
        routes.forEach { route ->
            var nextX = robot.first
            var nextY = robot.second
            val (direction, distance) = route.split(" ")
            when (direction) {
                "E" -> nextX += distance.toInt() * 1
                "W" -> nextX += distance.toInt() * -1
                "N" -> nextY += distance.toInt() * -1
                "S" -> nextY += distance.toInt() * 1
            }

            // 장외
            if (nextX < 0 || nextY < 0) return@forEach
            if (nextX >= row || nextY >= col) return@forEach
            // 가는 길 장애물
            for (i in minOf(robot.first, nextX)..maxOf(robot.first, nextX)) {
                for (j in minOf(robot.second, nextY)..maxOf(robot.second, nextY)) {
                    if (board[i][j] == BLOCK) return@forEach
                }
            }
            // 이동
            robot = Pair(nextX, nextY)
        }

        return intArrayOf(robot.second, robot.first)
    }

    /**
     * 테스트 1 〉	통과 (9.58ms, 62.5MB)
     * 테스트 2 〉	통과 (9.40ms, 61.9MB)
     * 테스트 3 〉	통과 (9.51ms, 62MB)
     * 테스트 4 〉	통과 (8.89ms, 61.5MB)
     * 테스트 5 〉	통과 (9.79ms, 60.7MB)
     * 테스트 6 〉	통과 (8.78ms, 60.2MB)
     * 테스트 7 〉	통과 (8.95ms, 61.8MB)
     * 테스트 8 〉	통과 (8.97ms, 62.4MB)
     * 테스트 9 〉	통과 (9.16ms, 62.1MB)
     * 테스트 10 〉	통과 (9.93ms, 62.4MB)
     * 테스트 11 〉	통과 (9.75ms, 63.7MB)
     * 테스트 12 〉	통과 (9.64ms, 63.6MB)
     * 테스트 13 〉	통과 (9.96ms, 61.1MB)
     * 테스트 14 〉	통과 (9.12ms, 62.9MB)
     * 테스트 15 〉	통과 (8.91ms, 62.9MB)
     * 테스트 16 〉	통과 (8.64ms, 61.8MB)
     * 테스트 17 〉	통과 (9.16ms, 61.1MB)
     * 테스트 18 〉	통과 (9.55ms, 63.1MB)
     * 테스트 19 〉	통과 (9.70ms, 62MB)
     * 테스트 20 〉	통과 (9.87ms, 61.3MB)
     */
    private fun run1(park: Array<String>, routes: Array<String>): IntArray {
        var answer = IntArray(2)
        // 이동 칸
        val directionMove = hashMapOf(
            "E" to intArrayOf(0, 1),
            "W" to intArrayOf(0, -1),
            "S" to intArrayOf(1, 0),
            "N" to intArrayOf(-1, 0)
        )

        // 초기 위치
        // forEach, forEachIndex는 반복적인 람다 호출로 퍼포먼스가 저하될 수 있기 때문에 for문 사용
        for (y in park.indices) {
            for (x in park[y].indices) {
                if (park[y][x] == 'S') {
                    answer[0] = y
                    answer[1] = x
                }
            }
        }

        // 이동
        routes.forEach { route ->
            val temp = route.split(" ")
            val move = directionMove[temp[0]]!!
            val step = temp[1].toInt()

            var y = answer[0]
            var x = answer[1]
            for (i in 0 until step) {
                y += move[0]
                x += move[1]

                // park.first() 보다 park[0]가 속도가 빠르다.
                if (y < 0 || x < 0 || x >= park[0].length || y >= park.size || park[y][x] == 'X') {
                    y = answer[0]
                    x = answer[1]
                    break
                }
            }
            answer[0] = y
            answer[1] = x
        }
        return answer
    }

    fun case1() {
        val park = arrayOf("SOO", "OOO", "OOO")
        val routes = arrayOf("E 2", "S 2", "W 1")
        val result = run1(park, routes)
        println("case1: (${result[0]} ${result[1]}), (2, 1)")
    }

    fun case2() {
        val park = arrayOf("SOO", "OXX", "OOO")
        val routes = arrayOf("E 2", "S 2", "W 1")
        val result = run1(park, routes)
        println("case2: (${result[0]} ${result[1]}), (0, 1)")
    }

    fun case3() {
        val park = arrayOf("OSO", "OOO", "OXO", "OOO")
        val routes = arrayOf("E 2", "S 3", "W 1")
        val result = run1(park, routes)
        println("case3: (${result[0]} ${result[1]}), (0, 0)")
    }
}