package org.example.algorithm

import org.example.algorithm.programmers.kakao_blind_2023.DeliveryAndCollectionOfParcels
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class AlgorithmApplication

fun main() {
    DeliveryAndCollectionOfParcels().case1()
    DeliveryAndCollectionOfParcels().case2()
}