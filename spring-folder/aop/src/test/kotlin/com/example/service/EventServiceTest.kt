package com.example.service

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class EventServiceTest @Autowired constructor(
    private val eventService: EventService
) {

    @Test
    fun eventTest() {
        eventService.create()
        eventService.process()
        eventService.remove()
    }
}