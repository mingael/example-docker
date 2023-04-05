package com.example.tracker

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(exclude = [DataSourceAutoConfiguration::class])
class TrackerApplication

fun main(args: Array<String>) {
    runApplication<TrackerApplication>(*args)
}