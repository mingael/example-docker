package com.example.tracker.click.domain

import java.time.LocalDateTime

data class SdkClickEvent(
    var id: Long? = null,
    val regDtm: LocalDateTime? = null
)