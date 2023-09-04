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

    private fun run1(park: Array<String>, routes: Array<String>): IntArray {
        var answer: IntArray = intArrayOf(0, 0)
        val order = HashMap<String, IntArray>()
        order["N"] = intArrayOf(-1, 0)
        order["S"] = intArrayOf(1, 0)
        order["W"] = intArrayOf(0, -1)
        order["E"] = intArrayOf(0, 1)

        for (i in park.indices) {
            for (j in park[i].indices) {
                if (park[i][j] == 'S') answer = intArrayOf(i, j)
            }
        }
        routes.forEach {
            val move = order[it.split(" ")[0]] //방향
            val count = it.split(" ")[1].toInt() // 이동 횟수
            var nr = answer[0]
            var nc = answer[1]

            for (i in 1..count) {
                nr += move!![0]
                nc += move[1]
                if (nr < 0 || nc < 0 || nr >= park.size || nc >= park[0].length || park[nr][nc] == 'X') {
                    nr = answer[0]
                    nc = answer[1]
                    break
                }
            }
            answer[0] = nr
            answer[1] = nc
        }

        return answer
    }

    fun case1() {
        val park = arrayOf("SOO", "OOO", "OOO")
        val routes = arrayOf("E 2", "S 2", "W 1")
        val result = run(park, routes)
        println("case1: (${result[0]} ${result[1]}), (2, 1)")
    }

    fun case2() {
        val park = arrayOf("SOO", "OXX", "OOO")
        val routes = arrayOf("E 2", "S 2", "W 1")
        val result = run(park, routes)
        println("case2: (${result[0]} ${result[1]}), (0, 1)")
    }

    fun case3() {
        val park = arrayOf("OSO", "OOO", "OXO", "OOO")
        val routes = arrayOf("E 2", "S 3", "W 1")
        val result = run(park, routes)
        println("case3: (${result[0]} ${result[1]}), (0, 0)")
    }
}