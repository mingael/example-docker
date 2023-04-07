package com.example.tracker.ad.service

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDateTime

@SpringBootTest
internal class AdEventServiceTest @Autowired constructor(
    private val adEventService: AdEventService
) {

    @Test
    fun saveTest() {
        for (i in 1..10) adEventService.save("session $i")
    }

    @Test
    fun findAllTest() {
        val result = adEventService.findAll()
        result.forEach { println(it) }
    }

    @Test
    fun findAdEventTest() {
        val result = adEventService.findAdEvent(LocalDateTime.now().minusMinutes(13))
        result.forEach { println(it) }
    }

}