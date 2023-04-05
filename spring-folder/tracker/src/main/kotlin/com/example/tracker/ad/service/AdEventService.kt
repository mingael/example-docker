package com.example.tracker.ad.service

import com.example.tracker.ad.domain.AdEvent
import com.example.tracker.ad.repository.AdEventRepository
import org.springframework.stereotype.Service
import java.time.LocalDate

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

    fun findAdEvent(regDtm: LocalDate): List<AdEvent> {
        return adEventRepository.findByRegDtm(regDtm)
    }

}