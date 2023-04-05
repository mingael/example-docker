package com.example.tracker.common.converter

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter
import java.time.LocalDate

class DynamoDBLocalDateConverter : DynamoDBTypeConverter<String, LocalDate> {
    override fun convert(date: LocalDate?): String? {
        return date?.toString()
    }

    override fun unconvert(value: String?): LocalDate? {
        return value?.let { LocalDate.parse(it) }
    }
}