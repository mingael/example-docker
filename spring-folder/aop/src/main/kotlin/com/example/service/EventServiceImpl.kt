package com.example.service

import com.example.annotation.PerfLogging
import org.springframework.stereotype.Service

@Service
class EventServiceImpl : EventService {

    override fun create() {
        println("creating event")
        Thread.sleep(10)
    }

    override fun process() {
        println("processing event")
        Thread.sleep(30)
    }

    @PerfLogging
    override fun remove() {
        println("remove event")
    }

}