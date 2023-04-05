package com.example.tracker.ad.service

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDate

@SpringBootTest
internal class AdEventServiceTest @Autowired constructor(
    private val adEventService: AdEventService
) {

    @Test
    fun saveTest() {
        adEventService.save("session")
    }

    @Test
    fun findAllTest() {
        val result = adEventService.findAll()
        result.forEach { println(it) }
    }

    @Test
    fun findAdEventTest() {
        val result = adEventService.findAdEvent(LocalDate.now())
        result.forEach { println(it) }
    }

}