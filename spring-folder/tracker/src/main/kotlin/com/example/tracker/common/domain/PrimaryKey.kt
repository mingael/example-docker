package com.example.tracker.common.domain

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted
import com.example.tracker.common.converter.DynamoDBLocalDateConverter
import java.time.LocalDate

data class PrimaryKey(
    @DynamoDBHashKey
    var id: Long? = null,

    @DynamoDBRangeKey(attributeName = "reg_dtm")
    @DynamoDBTypeConverted(converter = DynamoDBLocalDateConverter::class)
    var regDtm: LocalDate? = null
)