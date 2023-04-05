package com.example.tracker.click.repository

import com.example.tracker.click.domain.ClickEvent
import com.example.tracker.common.domain.PrimaryKey
import org.socialsignin.spring.data.dynamodb.repository.EnableScan
import org.springframework.data.repository.CrudRepository

@EnableScan
interface ClickEventRepository : CrudRepository<ClickEvent, PrimaryKey> {

    fun findByKey(key: PrimaryKey): List<ClickEvent>

}