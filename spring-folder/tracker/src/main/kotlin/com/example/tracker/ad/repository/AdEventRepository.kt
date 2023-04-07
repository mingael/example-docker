package com.example.tracker.ad.repository

import com.example.tracker.ad.domain.AdEvent
import org.socialsignin.spring.data.dynamodb.repository.EnableScan
import org.springframework.data.repository.CrudRepository
import java.time.LocalDateTime

@EnableScan
interface AdEventRepository : CrudRepository<AdEvent, String> {

    fun findByRegDtmGreaterThan(regDtm: LocalDateTime): List<AdEvent>

}