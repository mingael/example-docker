package org.example.algorithm

import org.example.algorithm.programmers.example.WalkInThePark
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class AlgorithmApplication

fun main() {
    WalkInThePark().case1()
    WalkInThePark().case2()
    WalkInThePark().case3()
}