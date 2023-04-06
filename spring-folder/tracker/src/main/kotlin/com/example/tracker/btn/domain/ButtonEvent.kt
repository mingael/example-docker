package com.example.tracker.btn.domain

import software.amazon.awssdk.enhanced.dynamodb.internal.converter.attribute.LocalDateTimeAttributeConverter
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.*
import java.time.LocalDateTime

@DynamoDbBean
data class ButtonEvent(
    @get:DynamoDbPartitionKey
    var id: Long? = null,

    @get:DynamoDbSortKey
    var username: String? = null,

    @get:DynamoDbAttribute("button_id")
    var buttonId: Long? = null,

    @get:DynamoDbAttribute("reg_dtm")
    @get:DynamoDbConvertedBy(LocalDateTimeAttributeConverter::class)
    var regDtm: LocalDateTime? = null
)