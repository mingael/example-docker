package com.example.tracker.scl.service

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.util.*

@SpringBootTest
class ScrollEventServiceTest @Autowired constructor(
    private val scrollEventService: ScrollEventService
) {
    companion object {
        const val id = "80814ff2-7d16-4618-b267-b9699f905649"
    }

    @Test
    fun putItemTest() {
        scrollEventService.putItem(
            key = "id",
            keyVal = UUID.randomUUID().toString(),
            columnKey1 = "user_id",
            columnKey1Val = "2"
        )
    }

    @Test
    fun getAllTest() {
        scrollEventService.getAll()
    }

    @Test
    fun getItemTest() {
        scrollEventService.getItem("id", id)
    }

    @Test
    fun queryDynTest1() {
        scrollEventService.keyConditionExpressionsForQuery("alias", "id", id)
    }

    @Test
    fun queryDynTest2() {
        scrollEventService.filterForQuery(
            filterKeyAlias = "u",
            filterKey = "user_id",
            filterKeyValAlias = "uv",
            filterKeyVal = "2",
            partitionAlias = "alias",
            partitionKeyName = id,
            partitionKeyVal = "id"
        )
    }

}