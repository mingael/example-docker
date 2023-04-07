package com.example.tracker.common.converter

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter
import java.time.LocalDateTime

class DynamoDbLocalDateTimeConverter : DynamoDBTypeConverter<String, LocalDateTime> {
    override fun convert(date: LocalDateTime?): String? {
        return date?.toString()
    }

    override fun unconvert(value: String?): LocalDateTime? {
        return value?.let { LocalDateTime.parse(it) }
    }
}