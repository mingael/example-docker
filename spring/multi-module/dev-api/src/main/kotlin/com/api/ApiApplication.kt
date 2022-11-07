package com.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DevApiApplication

fun main(args: Array<String>) {
    runApplication<DevApiApplication>(*args)
}
