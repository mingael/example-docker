package com.example.tracker.click.domain

import com.example.tracker.common.converter.SdkLocalDateConverter
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.*
import java.time.LocalDateTime

@DynamoDbBean
data class SdkClickEvent(
    @get:DynamoDbPartitionKey
    var id: Long? = null,

    @get:DynamoDbSortKey
    @get:DynamoDbConvertedBy(SdkLocalDateConverter::class)
    val regDtm: LocalDateTime? = null
)