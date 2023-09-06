package com.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@ComponentScan(basePackages = ["com.api"])
@SpringBootApplication
class DevApiApplication

fun main(args: Array<String>) {
    runApplication<DevApiApplication>(*args)
}
