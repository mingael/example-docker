package com.example.tracker.click.service

import com.example.tracker.click.vo.JsonData
import com.example.tracker.common.domain.PrimaryKey
import com.google.gson.Gson
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDateTime

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
        result.forEach { println("----$it") }
    }

    @Test
    fun findByKeyTest() {
        val result = clickEventService.findByKey(PrimaryKey(id = 1L, regDtm = LocalDateTime.now()))
        result.forEach { println("-----$it") }
    }

    @Test
    fun findByRegDtmBetweenTest() {
        val now = LocalDateTime.now()
        val result = clickEventService.findByRegDtmBetween(
            8L,
            now.minusDays(1),
            now.plusDays(1)
        )
        result.forEach { e ->
            println(e)
            e.jsonData?.run { println(Gson().fromJson(this, JsonData::class.java)) }
        }
    }

}