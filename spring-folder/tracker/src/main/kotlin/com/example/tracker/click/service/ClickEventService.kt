package com.example.tracker.click.service

import com.example.tracker.click.domain.ClickEvent
import com.example.tracker.click.repository.ClickEventRepository
import com.example.tracker.common.domain.PrimaryKey
import org.springframework.stereotype.Service
import java.time.LocalDate


@Service
class ClickEventService(
    private val clickEventRepository: ClickEventRepository
) {

    fun save() {
        clickEventRepository.save(ClickEvent(id = 8L, regDtm = LocalDate.now()))
    }

    fun findAll(): List<ClickEvent> {
        return clickEventRepository.findAll() as List<ClickEvent>
    }

    fun findByKey(key: PrimaryKey): List<ClickEvent> {
        return clickEventRepository.findByKey(key)
    }

}