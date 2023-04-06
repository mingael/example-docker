package com.example.tracker.common.converter

import software.amazon.awssdk.enhanced.dynamodb.AttributeConverter
import software.amazon.awssdk.enhanced.dynamodb.AttributeValueType
import software.amazon.awssdk.enhanced.dynamodb.EnhancedType
import software.amazon.awssdk.services.dynamodb.model.AttributeValue
import java.time.LocalDateTime

/**
 * LocalDateTimeAttributeConverter Custom
 */
class SdkLocalDateConverter : AttributeConverter<LocalDateTime> {
    override fun transformFrom(input: LocalDateTime): AttributeValue {
        return AttributeValue.builder().s(input.toString()).build()
    }

    override fun transformTo(input: AttributeValue): LocalDateTime {
        return LocalDateTime.parse(input.s())
    }

    override fun type(): EnhancedType<LocalDateTime> {
        return EnhancedType.of(LocalDateTime::class.java)
    }

    override fun attributeValueType(): AttributeValueType {
        return AttributeValueType.S
    }
}