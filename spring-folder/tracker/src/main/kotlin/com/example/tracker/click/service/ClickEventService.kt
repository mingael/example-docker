package com.example.tracker.click.service

import com.example.tracker.click.domain.ClickEvent
import com.example.tracker.click.domain.SdkClickEvent
import com.example.tracker.click.repository.ClickEventRepository
import com.example.tracker.click.repository.SdkClickEventRepository
import com.example.tracker.click.vo.JsonData
import com.example.tracker.common.domain.PrimaryKey
import com.google.gson.Gson
import org.springframework.stereotype.Service
import java.time.LocalDateTime


@Service
class ClickEventService(
    private val clickEventRepository: ClickEventRepository,
    private val sdkClickEventRepository: SdkClickEventRepository
) {

    fun save() {
        val jsonData = Gson().toJson(JsonData("abcd"))
        clickEventRepository.save(
            ClickEvent(
                id = 8L,
                regDtm = LocalDateTime.now(),
                jsonData = jsonData
            )
        )
    }

    fun findAll(): List<ClickEvent> {
        return clickEventRepository.findAll() as List<ClickEvent>
    }

    fun findByKey(key: PrimaryKey): List<ClickEvent> {
        return clickEventRepository.findByKey(key)
    }

    fun findByRegDtmBetween(
        partitionVal: Long,
        startDtm: LocalDateTime,
        endDtm: LocalDateTime
    ): List<SdkClickEvent> {
        return sdkClickEventRepository.findByRegDtmBetween(partitionVal, startDtm, endDtm)
    }

}