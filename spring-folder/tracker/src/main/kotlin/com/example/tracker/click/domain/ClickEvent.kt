package com.example.tracker.click.domain

import com.amazonaws.services.dynamodbv2.datamodeling.*
import com.example.tracker.common.converter.DynamoDbLocalDateTimeConverter
import com.example.tracker.common.domain.PrimaryKey
import java.time.LocalDateTime

/**
 * DynamoDB는 Schema가 없기 때문에 기본값을 NULL로 하는게 안전
 */
@DynamoDBTable(tableName = "click_event")
data class ClickEvent(
    @get:DynamoDBHashKey
    var id: Long? = null,

    @get:DynamoDBRangeKey(attributeName = "reg_dtm")
    @DynamoDBTypeConverted(converter = DynamoDbLocalDateTimeConverter::class)
    var regDtm: LocalDateTime? = null,

    @DynamoDBAttribute(attributeName = "json_data")
    var jsonData: String? = null
) {

    // 아직 jakarta.persistence.Id 지원안함
    @org.springframework.data.annotation.Id
    private var key: PrimaryKey? = null
        get() {
            return PrimaryKey(id, regDtm)
        }

}
