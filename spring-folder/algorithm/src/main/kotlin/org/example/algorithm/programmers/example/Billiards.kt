package org.example.algorithm.programmers.example

class Billiards {

    /**
     * 테스트 1 〉	통과 (8.88ms, 62.2MB)
     * 테스트 2 〉	통과 (6.64ms, 62MB)
     * 테스트 3 〉	통과 (6.28ms, 60.8MB)
     * 테스트 4 〉	통과 (6.41ms, 62.3MB)
     * 테스트 5 〉	통과 (8.59ms, 61.7MB)
     * 테스트 6 〉	통과 (9.45ms, 62.6MB)
     * 테스트 7 〉	통과 (6.71ms, 61.7MB)
     * 테스트 8 〉	통과 (8.41ms, 60.6MB)
     * 테스트 9 〉	통과 (6.40ms, 61.3MB)
     * 테스트 10 〉	통과 (8.83ms, 62.5MB)
     * 테스트 11 〉	통과 (8.15ms, 61.9MB)
     * 테스트 12 〉	통과 (10.32ms, 60.2MB)
     * 테스트 13 〉	통과 (9.62ms, 61.8MB)
     * 테스트 14 〉	통과 (7.74ms, 62.2MB)
     * 테스트 15 〉	통과 (5.85ms, 62.1MB)
     * 테스트 16 〉	통과 (6.16ms, 62MB)
     * 테스트 17 〉	통과 (7.07ms, 63.2MB)
     * 테스트 18 〉	통과 (7.62ms, 61.8MB)
     * 테스트 19 〉	통과 (5.99ms, 62MB)
     * 테스트 20 〉	통과 (7.50ms, 61.4MB)
     * 테스트 21 〉	통과 (8.75ms, 62.7MB)
     * 테스트 22 〉	통과 (8.80ms, 62.1MB)
     * 테스트 23 〉	통과 (8.20ms, 60.7MB)
     * 테스트 24 〉	통과 (7.71ms, 62.9MB)
     * 테스트 25 〉	통과 (6.34ms, 62.3MB)
     * 테스트 26 〉	통과 (6.18ms, 61.8MB)
     * 테스트 27 〉	통과 (7.95ms, 61.3MB)
     * 테스트 28 〉	통과 (9.28ms, 61.6MB)
     * 테스트 29 〉	통과 (8.58ms, 60.9MB)
     * 테스트 30 〉	통과 (7.30ms, 59.9MB)
     * 테스트 31 〉	통과 (8.80ms, 61.7MB)
     * 테스트 32 〉	통과 (9.68ms, 62.2MB)
     * 테스트 33 〉	통과 (5.60ms, 61.6MB)
     * 테스트 34 〉	통과 (14.56ms, 62.1MB)
     * 테스트 35 〉	통과 (5.96ms, 62.5MB)
     * 테스트 36 〉	통과 (8.47ms, 62.4MB)
     */
    fun run(m: Int, n: Int, startX: Int, startY: Int, balls: Array<IntArray>): IntArray {
        val answer = ArrayList<Int>()
        for (i in balls.indices) answer.add(getMinDistance(m, n, startX, startY, balls[i]))
        return answer.toIntArray()
    }

    /**
     * 최소 거리 구하기
     * - 상하좌우 모든 방향에 삼각형을 그려서 최소 거리를 구한다.
     * - X 좌표나 Y 좌표가 동일한 경우는 제외한다.
     */
    private fun getMinDistance(width: Int, height: Int, startX: Int, startY: Int, ball: IntArray): Int {
        var min = Integer.MAX_VALUE
        // 아래 방향으로 삼각형 그리기. 타겟 공이 동일한 X축 아래에 있는 경우 제외.
        if (!(ball[0] == startX && ball[1] < startY))
            min = minOf(min, calculateSlopeSquareRoot(startX, startY, ball[0], ball[1] * -1))
        // 위 방향으로 삼각형 그리기. 타겟 공이 동일한 X축 위에 있는 경우 제외.
        if (!(ball[0] == startX && ball[1] > startY))
            min = minOf(min, calculateSlopeSquareRoot(startX, startY, ball[0], 2 * height - ball[1]))
        // 왼쪽 방향으로 삼각형 그리기. 타겟 공이 동일한 Y축 왼쪽에 있는 경우 제외.
        if (!(ball[1] == startY && ball[0] < startX))
            min = minOf(min, calculateSlopeSquareRoot(startX, startY, ball[0] * -1, ball[1]))
        // 오른쪽 방향으로 삼각형 그리기. 타겟 공이 동일한 Y축 오른쪽에 있는 경우 제외.
        if (!(ball[1] == startY && ball[0] > startX))
            min = minOf(min, calculateSlopeSquareRoot(startX, startY, 2 * width - ball[0], ball[1]))
        return min
    }

    /**
     * 기울기 제곱근 계산
     * - 대각선^2 = 가로^2 + 세로^2
     */
    private fun calculateSlopeSquareRoot(startX: Int, startY: Int, targetX: Int, targetY: Int): Int {
        // kotlin.math.pow 사용하는 속도와 큰 차이는 없는 듯 하다
        val diffX = startX - targetX
        val diffY = startY - targetY
        return diffX * diffX + diffY * diffY
    }

    fun case1() {
        val result = run(10, 10, 3, 7, arrayOf(intArrayOf(7, 7), intArrayOf(2, 7), intArrayOf(7, 3)))
        println("case1: ${result.joinToString(",")}")
    }

}