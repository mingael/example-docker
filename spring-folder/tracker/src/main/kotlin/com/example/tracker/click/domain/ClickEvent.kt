package com.example.tracker.click.domain

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted
import com.example.tracker.common.converter.DynamoDBLocalDateConverter
import com.example.tracker.common.domain.PrimaryKey
import java.time.LocalDate

/**
 * DynamoDB는 Schema가 없기 때문에 기본값을 NULL로 하는게 안전
 */
@DynamoDBTable(tableName = "click_event")
data class ClickEvent(
    @get:DynamoDBHashKey
    var id: Long? = null,

    @get:DynamoDBRangeKey(attributeName = "reg_dtm")
    @DynamoDBTypeConverted(converter = DynamoDBLocalDateConverter::class)
    var regDtm: LocalDate? = null
) {

    // 아직 jakarta.persistence.Id 지원안함
    @org.springframework.data.annotation.Id
    private var key: PrimaryKey? = null
        get() {
            return PrimaryKey(id, regDtm)
        }

}
