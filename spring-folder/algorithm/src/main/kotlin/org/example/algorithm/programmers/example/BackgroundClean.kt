package org.example.algorithm.programmers.example

import java.lang.Integer.max
import java.lang.Integer.min

class BackgroundClean {

    /**
     * 테스트 1 〉	통과 (0.03ms, 58.5MB)
     * 테스트 2 〉	통과 (0.02ms, 61.2MB)
     * 테스트 3 〉	통과 (0.02ms, 61.9MB)
     * 테스트 4 〉	통과 (0.03ms, 61MB)
     * 테스트 5 〉	통과 (0.02ms, 61.7MB)
     * 테스트 6 〉	통과 (0.03ms, 60.7MB)
     * 테스트 7 〉	통과 (0.06ms, 58.6MB)
     * 테스트 8 〉	통과 (0.09ms, 60.1MB)
     * 테스트 9 〉	통과 (0.17ms, 59.4MB)
     * 테스트 10 〉	통과 (0.05ms, 59.3MB)
     * 테스트 11 〉	통과 (0.05ms, 59.6MB)
     * 테스트 12 〉	통과 (0.05ms, 61.1MB)
     * 테스트 13 〉	통과 (0.05ms, 58.4MB)
     * 테스트 14 〉	통과 (0.05ms, 59.1MB)
     * 테스트 15 〉	통과 (0.09ms, 58.8MB)
     * 테스트 16 〉	통과 (0.16ms, 59.1MB)
     * 테스트 17 〉	통과 (0.04ms, 58.6MB)
     * 테스트 18 〉	통과 (0.15ms, 60.9MB)
     * 테스트 19 〉	통과 (0.09ms, 57.9MB)
     * 테스트 20 〉	통과 (0.11ms, 58.5MB)
     * 테스트 21 〉	통과 (0.06ms, 64.2MB)
     * 테스트 22 〉	통과 (0.03ms, 60.8MB)
     * 테스트 23 〉	통과 (0.03ms, 61.8MB)
     * 테스트 24 〉	통과 (0.03ms, 59.2MB)
     * 테스트 25 〉	통과 (0.12ms, 61.7MB)
     * 테스트 26 〉	통과 (0.11ms, 60.1MB)
     * 테스트 27 〉	통과 (0.07ms, 61MB)
     * 테스트 28 〉	통과 (0.05ms, 60.2MB)
     * 테스트 29 〉	통과 (0.04ms, 61.5MB)
     * 테스트 30 〉	통과 (0.13ms, 61.9MB)
     * 테스트 31 〉	통과 (0.08ms, 60.9MB)
     */
    fun run(wallpaper: Array<String>): IntArray {
        val yCount = wallpaper.size
        val xCount = wallpaper[0].length

        var answer = intArrayOf(yCount, xCount, 0, 0)

        for (y in wallpaper.indices) {
            for (x in 0 until xCount) {
                if (wallpaper[y][x] == '#') {
                    if (answer[0] > y) answer[0] = y
                    if (answer[1] > x) answer[1] = x
                    if (answer[2] < y + 1) answer[2] = y + 1
                    if (answer[3] < x + 1) answer[3] = x + 1
                }
            }
        }

        return answer
    }

    /**
     * 리스트 정렬 이용하기
     *
     * 테스트 1 〉	통과 (28.59ms, 63.4MB)
     * 테스트 2 〉	통과 (17.91ms, 63.8MB)
     * 테스트 3 〉	통과 (23.09ms, 63.5MB)
     * 테스트 4 〉	통과 (20.25ms, 63.4MB)
     * 테스트 5 〉	통과 (17.93ms, 63.8MB)
     * 테스트 6 〉	통과 (20.46ms, 64.5MB)
     * 테스트 7 〉	통과 (31.97ms, 63.5MB)
     * 테스트 8 〉	통과 (20.66ms, 64.7MB)
     * 테스트 9 〉	통과 (35.47ms, 65.2MB)
     * 테스트 10 〉	통과 (25.70ms, 63.9MB)
     * 테스트 11 〉	통과 (21.01ms, 63.5MB)
     * 테스트 12 〉	통과 (24.09ms, 63.1MB)
     * 테스트 13 〉	통과 (18.18ms, 64.1MB)
     * 테스트 14 〉	통과 (24.74ms, 64.4MB)
     * 테스트 15 〉	통과 (17.37ms, 63.3MB)
     * 테스트 16 〉	통과 (17.00ms, 63.4MB)
     * 테스트 17 〉	통과 (23.02ms, 65.5MB)
     * 테스트 18 〉	통과 (19.49ms, 63.4MB)
     * 테스트 19 〉	통과 (18.75ms, 63.9MB)
     * 테스트 20 〉	통과 (25.74ms, 64MB)
     * 테스트 21 〉	통과 (19.03ms, 64.1MB)
     * 테스트 22 〉	통과 (25.72ms, 63.7MB)
     * 테스트 23 〉	통과 (28.70ms, 63.9MB)
     * 테스트 24 〉	통과 (19.58ms, 64.4MB)
     * 테스트 25 〉	통과 (18.57ms, 63.5MB)
     * 테스트 26 〉	통과 (24.57ms, 63.4MB)
     * 테스트 27 〉	통과 (21.75ms, 63.5MB)
     * 테스트 28 〉	통과 (26.93ms, 64MB)
     * 테스트 29 〉	통과 (20.68ms, 64.6MB)
     * 테스트 30 〉	통과 (21.62ms, 63.5MB)
     * 테스트 31 〉	통과 (8.25ms, 62.8MB)
     */
    fun run3(wallpaper: Array<String>): IntArray {
        val map = mutableListOf<Pair<Int, Int>>()

        for (y in wallpaper.indices) {
            for (x in 0 until wallpaper[0].length) {
                if (wallpaper[y][x] == '#') {
                    map.add(Pair(y, x))
                }
            }
        }

        val ySortedMap = map.sortedBy { it.first }
        val xSortedMap = map.sortedBy { it.second }
        return intArrayOf(
            ySortedMap.first().first,
            xSortedMap.first().second,
            ySortedMap.last().first + 1,
            xSortedMap.last().second + 1
        )
    }

    /**
     * 내장함수 이용하기
     *
     * 테스트 1 〉	통과 (0.73ms, 59.2MB)
     * 테스트 2 〉	통과 (0.68ms, 60MB)
     * 테스트 3 〉	통과 (0.46ms, 62.3MB)
     * 테스트 4 〉	통과 (0.47ms, 61.7MB)
     * 테스트 5 〉	통과 (0.51ms, 61.7MB)
     * 테스트 6 〉	통과 (0.46ms, 61.4MB)
     * 테스트 7 〉	통과 (0.53ms, 62MB)
     * 테스트 8 〉	통과 (0.87ms, 61.3MB)
     * 테스트 9 〉	통과 (1.08ms, 60MB)
     * 테스트 10 〉	통과 (0.57ms, 60.5MB)
     * 테스트 11 〉	통과 (0.66ms, 61.2MB)
     * 테스트 12 〉	통과 (0.78ms, 61.5MB)
     * 테스트 13 〉	통과 (0.47ms, 59.7MB)
     * 테스트 14 〉	통과 (0.71ms, 60.2MB)
     * 테스트 15 〉	통과 (0.68ms, 61.6MB)
     * 테스트 16 〉	통과 (0.88ms, 62.1MB)
     * 테스트 17 〉	통과 (0.67ms, 59.3MB)
     * 테스트 18 〉	통과 (0.60ms, 60.1MB)
     * 테스트 19 〉	통과 (0.83ms, 61.2MB)
     * 테스트 20 〉	통과 (0.73ms, 61.4MB)
     * 테스트 21 〉	통과 (0.45ms, 61.9MB)
     * 테스트 22 〉	통과 (0.67ms, 61.7MB)
     * 테스트 23 〉	통과 (0.54ms, 59.5MB)
     * 테스트 24 〉	통과 (0.44ms, 61.1MB)
     * 테스트 25 〉	통과 (0.88ms, 61MB)
     * 테스트 26 〉	통과 (0.90ms, 60.6MB)
     * 테스트 27 〉	통과 (0.57ms, 61.7MB)
     * 테스트 28 〉	통과 (0.58ms, 62.4MB)
     * 테스트 29 〉	통과 (0.54ms, 62MB)
     * 테스트 30 〉	통과 (0.79ms, 61.8MB)
     * 테스트 31 〉	통과 (0.51ms, 62.2MB)
     */
    fun run2(wallpaper: Array<String>): IntArray {
        val yCount = wallpaper.size
        val xCount = wallpaper[0].length

        var (yMin, xMin) = (yCount - 1 to xCount - 1)
        var (yMax, xMax) = (0 to 0)
        for (y in wallpaper.indices) {
            for (x in 0 until xCount) {
                if (wallpaper[y][x] == '#') {
                    yMin = min(y, yMin)
                    xMin = min(x, xMin)
                    yMax = max(y, yMax)
                    xMax = max(x, xMax)
                }
            }
        }
        return intArrayOf(yMin, xMin, yMax + 1, xMax + 1)
    }

    /**
     * 테스트 1 〉	통과 (0.28ms, 62.6MB)
     * 테스트 2 〉	통과 (0.32ms, 60.6MB)
     * 테스트 3 〉	통과 (0.26ms, 61.2MB)
     * 테스트 4 〉	통과 (0.31ms, 62.1MB)
     * 테스트 5 〉	통과 (0.40ms, 59.1MB)
     * 테스트 6 〉	통과 (0.26ms, 62MB)
     * 테스트 7 〉	통과 (0.40ms, 62.5MB)
     * 테스트 8 〉	통과 (0.41ms, 61.6MB)
     * 테스트 9 〉	통과 (0.80ms, 60.5MB)
     * 테스트 10 〉	통과 (0.35ms, 62.3MB)
     * 테스트 11 〉	통과 (0.39ms, 62MB)
     * 테스트 12 〉	통과 (0.42ms, 61.2MB)
     * 테스트 13 〉	통과 (0.33ms, 62.6MB)
     * 테스트 14 〉	통과 (0.42ms, 60.9MB)
     * 테스트 15 〉	통과 (0.36ms, 60.8MB)
     * 테스트 16 〉	통과 (0.35ms, 61.8MB)
     * 테스트 17 〉	통과 (0.48ms, 61.2MB)
     * 테스트 18 〉	통과 (0.40ms, 61.8MB)
     * 테스트 19 〉	통과 (0.44ms, 61.5MB)
     * 테스트 20 〉	통과 (0.71ms, 60MB)
     * 테스트 21 〉	통과 (0.27ms, 62.1MB)
     * 테스트 22 〉	통과 (0.27ms, 61MB)
     * 테스트 23 〉	통과 (0.28ms, 61.3MB)
     * 테스트 24 〉	통과 (0.37ms, 62.2MB)
     * 테스트 25 〉	통과 (0.36ms, 62.3MB)
     * 테스트 26 〉	통과 (0.41ms, 61.6MB)
     * 테스트 27 〉	통과 (0.29ms, 62.1MB)
     * 테스트 28 〉	통과 (0.45ms, 61.6MB)
     * 테스트 29 〉	통과 (0.47ms, 63.1MB)
     * 테스트 30 〉	통과 (0.74ms, 59.7MB)
     * 테스트 31 〉	통과 (0.36ms, 60.7MB)
     */
    fun run1(wallpaper: Array<String>): IntArray {
        val yCount = wallpaper.size
        val xCount = wallpaper[0].length

        var min = Pair(yCount - 1, xCount - 1)
        var max = Pair(0, 0)
        for (y in wallpaper.indices) {
            for (x in 0 until xCount) {
                if (wallpaper[y][x] == '#') {
                    if (y < min.first) min = Pair(y, min.second)
                    if (x < min.second) min = Pair(min.first, x)
                    if (y > max.first) max = Pair(y, max.second)
                    if (x > max.second) max = Pair(max.first, x)
                }
            }
        }
        return intArrayOf(min.first, min.second, max.first + 1, max.second + 1)
    }

}