package com.dev.home

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@EnableJpaAuditing
@SpringBootApplication
@EntityScan(basePackages = ["com.dev.core"])
@EnableJpaRepositories(basePackages = ["com.dev.core"])
class DevHomeApplication

fun main(args: Array<String>) {
    runApplication<DevHomeApplication>(*args)
}