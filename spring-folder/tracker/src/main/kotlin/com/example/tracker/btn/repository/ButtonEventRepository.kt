package com.example.tracker.btn.repository

import com.example.tracker.btn.domain.ButtonEvent
import io.reactivex.Flowable
import org.springframework.stereotype.Repository
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient
import software.amazon.awssdk.enhanced.dynamodb.Expression
import software.amazon.awssdk.enhanced.dynamodb.Key
import software.amazon.awssdk.enhanced.dynamodb.TableSchema
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional
import software.amazon.awssdk.services.dynamodb.model.AttributeValue
import java.time.LocalDateTime
import java.util.concurrent.CompletableFuture


@Repository
class ButtonEventRepository(
    private val client: DynamoDbEnhancedAsyncClient
) {

    companion object {
        private const val TABLE_NAME = "button_event"
        private val tableSchema = TableSchema.fromBean(ButtonEvent::class.java)
    }

    private val table = client.table(TABLE_NAME, tableSchema)

    fun save(buttonEvent: ButtonEvent) {
        table.putItem(buttonEvent).get()
    }

    fun findByKey(partitionVal: Long, sortVal: String): ButtonEvent? {
        val key = Key.builder().partitionValue(partitionVal).sortValue(sortVal).build()
        val result: CompletableFuture<ButtonEvent>? = table.getItem { it.key(key) }
        return result?.get()
    }

    fun findByKeyAndRegDtm(
        partitionVal: Long,
        sortVal: String,
        startDtm: LocalDateTime,
        endDtm: LocalDateTime
    ): List<ButtonEvent> {
        val values = mutableMapOf<String, AttributeValue>()
        values[":start_dtm"] = AttributeValue.builder().s(startDtm.toString()).build()
        values[":end_dtm"] = AttributeValue.builder().s(endDtm.toString()).build()

        val expression = Expression.builder()
            .expression("reg_dtm >= :start_dtm AND reg_dtm <= :end_dtm")
            .expressionValues(values)
            .build()

        val queryConditional = QueryConditional
            .keyEqualTo(Key.builder().partitionValue(partitionVal).sortValue(sortVal).build())

        val result = table.query { r ->
            r.queryConditional(queryConditional)
                .filterExpression(expression)
        }.items()

        return Flowable.fromPublisher(result).toList().blockingGet()
    }

}