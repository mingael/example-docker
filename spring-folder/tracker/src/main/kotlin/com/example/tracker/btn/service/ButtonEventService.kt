package com.example.tracker.btn.service

import com.example.tracker.btn.domain.ButtonEvent
import com.example.tracker.btn.repository.ButtonEventRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class ButtonEventService(
    private val buttonEventRepository: ButtonEventRepository
) {

    fun save(buttonEvent: ButtonEvent) {
        buttonEventRepository.save(buttonEvent)
    }

    fun findByKey(partitionVal: Long, sortVal: String): ButtonEvent? {
        return buttonEventRepository.findByKey(partitionVal, sortVal)
    }

    fun findByKeyAndRegDtm(
        partitionVal: Long,
        sortVal: String,
        startDtm: LocalDateTime,
        endDtm: LocalDateTime
    ): List<ButtonEvent> {
        return buttonEventRepository.findByKeyAndRegDtm(partitionVal, sortVal, startDtm, endDtm)
    }

}