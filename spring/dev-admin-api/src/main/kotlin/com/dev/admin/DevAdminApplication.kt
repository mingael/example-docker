package com.dev.admin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DevAdminApplication

fun main(args: Array<String>) {
    runApplication<DevAdminApplication>(*args)
}