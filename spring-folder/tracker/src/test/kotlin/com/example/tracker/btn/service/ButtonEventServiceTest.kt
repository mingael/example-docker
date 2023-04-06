package com.example.tracker.btn.service

import com.example.tracker.btn.domain.ButtonEvent
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDateTime

@SpringBootTest
class ButtonEventServiceTest @Autowired constructor(
    private val buttonEventService: ButtonEventService
) {
    companion object {
        const val ID = 1L
        const val USER = "2"
    }

    @Test
    fun saveTest() {
        val btn = ButtonEvent(id = ID, username = USER, regDtm = LocalDateTime.now())
        buttonEventService.save(btn)
    }

    @Test
    fun findByIdTest() {
        val btn = buttonEventService.findByKey(partitionVal = ID, sortVal = USER)
        println("btn entity: $btn")
        println("id: ${btn?.id}")
        println("btn id: ${btn?.buttonId}")
        println("btn regDtm: ${btn?.regDtm}")
    }

    @Test
    fun findByIdAndRegDtmTest() {
        val dtm = LocalDateTime.now()
        val start = dtm.minusDays(1)
        val btns = buttonEventService.findByKeyAndRegDtm(
            partitionVal = ID,
            sortVal = USER,
            startDtm = start,
            endDtm = dtm
        )
        btns.forEach { btn ->
            println("-- id: ${btn.id} / btn id: ${btn.buttonId}")
        }
    }
}