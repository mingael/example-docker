package com.example.tracker.click.service

import com.example.tracker.common.domain.PrimaryKey
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDate

@SpringBootTest
internal class ClickEventServiceTest @Autowired constructor(
    private val clickEventService: ClickEventService
) {

    @Test
    fun saveTest() {
        clickEventService.save()
    }

    @Test
    fun findAllTest() {
        val result = clickEventService.findAll()
        result.forEach { println(it) }
    }


    @Test
    fun findByKeyTest() {
        val result = clickEventService.findByKey(PrimaryKey(id = 1L, regDtm = LocalDate.now()))
        result.forEach { println(it) }
    }

}