package com.example.tracker.click.repository

import aws.sdk.kotlin.services.dynamodb.model.AttributeValue
import aws.sdk.kotlin.services.dynamodb.model.QueryRequest
import com.example.tracker.click.domain.SdkClickEvent
import com.example.tracker.common.provider.DynamoDbClientProvider
import kotlinx.coroutines.runBlocking
import org.springframework.stereotype.Repository
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Repository
class SdkClickEventRepository(
    private val clientProvider: DynamoDbClientProvider
) {

    companion object {
        const val TABLE_NAME = "click_event"
    }

    fun findByRegDtmBetween(
        partitionVal: Long,
        startDtm: LocalDateTime,
        endDtm: LocalDateTime
    ): List<SdkClickEvent> {
        val list = mutableListOf<SdkClickEvent>()

        runBlocking {
            val attr = mutableMapOf<String, AttributeValue>()
            attr[":a"] = AttributeValue.N(partitionVal.toString())
            attr[":t1"] = AttributeValue.S(startDtm.toString())
            attr[":t2"] = AttributeValue.S(endDtm.toString())
//            attr[":t"] = AttributeValue.S("2023-04-07")

            val request = QueryRequest {
                tableName = TABLE_NAME
                keyConditionExpression = "id = :a AND reg_dtm BETWEEN :t1 AND :t2"
//                keyConditionExpression = "id = :a AND begins_with(reg_dtm, :t)"
                expressionAttributeValues = attr
            }
            clientProvider.client().use { ddb ->
                ddb.query(request).items?.forEach {
                    val id = it.getValue("id").asN()
                    val regDtm = it.getValue("reg_dtm").asS()
                    val jsonData = it.getValue("json_data").asS()
                    list.add(
                        SdkClickEvent(
                            id = id.toLong(),
                            regDtm = LocalDateTime.parse(regDtm, DateTimeFormatter.ISO_DATE_TIME),
                            jsonData = jsonData
                        )
                    )
                }
            }
        }

        return list
    }

    /*
        //client: DynamoDbEnhancedAsyncClient

        companion object {
            private const val TABLE_NAME = "click_event"
            private val tableSchema = TableSchema.fromBean(SdkClickEvent::class.java)
        }

        private val table = client.table(TABLE_NAME, tableSchema)

        fun findByRegDtmBetween(
            partitionVal: Long,
            startDtm: LocalDateTime,
            endDtm: LocalDateTime
        ): List<SdkClickEvent> {
            val fromKey = Key.builder().partitionValue(partitionVal).sortValue(startDtm.toString()).build()
            val toKey = Key.builder().partitionValue(partitionVal).sortValue(endDtm.toString()).build()

            val request = QueryEnhancedRequest.builder()
                .queryConditional(QueryConditional.sortBetween(fromKey, toKey))
                .limit(10)
                .build()
            val result = table.query(request).items()
            return Flowable.fromPublisher(result).toList().blockingGet()
        }
     */
}