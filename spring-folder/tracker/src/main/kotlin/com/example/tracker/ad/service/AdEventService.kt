package com.example.tracker.ad.service

import com.example.tracker.ad.domain.AdEvent
import com.example.tracker.ad.repository.AdEventRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class AdEventService(
    private val adEventRepository: AdEventRepository
) {

    fun save(sessionKey: String) {
        adEventRepository.save(
            AdEvent(
                sessionKey = sessionKey
            )
        )
    }

    fun findAll(): List<AdEvent> {
        return adEventRepository.findAll() as List<AdEvent>
    }

    fun findAdEvent(regDtm: LocalDateTime): List<AdEvent> {
        return adEventRepository.findByRegDtmGreaterThan(regDtm)
    }

}