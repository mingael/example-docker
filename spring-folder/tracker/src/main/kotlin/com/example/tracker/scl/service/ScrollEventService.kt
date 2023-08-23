package com.example.tracker.scl.service

import aws.sdk.kotlin.runtime.auth.credentials.EnvironmentCredentialsProvider
import aws.sdk.kotlin.services.dynamodb.DynamoDbClient
import aws.sdk.kotlin.services.dynamodb.model.*
import aws.smithy.kotlin.runtime.net.Url
import kotlinx.coroutines.runBlocking
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class ScrollEventService(
    @Value("\${cloud.aws.dynamodb.endpoint}") private val endpoint: String,
    @Value("\${cloud.aws.region.static}") private val regions: String
) {
    companion object {
        const val TABLE_NAME = "scroll_event"
    }

    fun getAll() {
        runBlocking {
            val request = ScanRequest {
                tableName = "scroll_event"
            }

            getClient().use { ddb ->
                val response = ddb.scan(request)
                response.items?.forEach { item ->
                    item.keys.forEach { key ->
                        println("key name: $key / value: ${item[key]}")
                    }
                }
            }
        }
    }

    fun putItem(
        key: String,
        keyVal: String,
        columnKey1: String,
        columnKey1Val: String
    ) {
        runBlocking {
            val itemValues = mutableMapOf<String, AttributeValue>()
            itemValues[key] = AttributeValue.S(keyVal)
            itemValues[columnKey1] = AttributeValue.S(columnKey1Val)

            val request = PutItemRequest {
                tableName = TABLE_NAME
                item = itemValues
            }

            getClient().use { ddb ->
                ddb.putItem(request)
                println("put item: $TABLE_NAME / key val: $keyVal")
            }
        }
    }

    /**
     * @param keyName = Required key
     */
    fun getItem(keyName: String, keyVal: String) {
        runBlocking {
            val keyToGet = mutableMapOf<String, AttributeValue>()
            keyToGet[keyName] = AttributeValue.S(keyVal)

            val request = GetItemRequest {
                key = keyToGet
                tableName = TABLE_NAME
            }

            getClient().use { ddb ->
                val returnedItem = ddb.getItem(request)
                val numberMap = returnedItem.item
                numberMap?.forEach { key ->
                    println("key name: ${key.key} / value: ${key.value}")
                }
            }
        }
    }

    fun keyConditionExpressionsForQuery(
        partitionAlias: String,
        partitionKeyName: String,
        partitionKeyVal: String
    ) {
        runBlocking {
            val attrValues = mutableMapOf<String, AttributeValue>()
            attrValues[":$partitionAlias"] = AttributeValue.S(partitionKeyVal)

            val request = QueryRequest {
                tableName = TABLE_NAME
                keyConditionExpression = "$partitionKeyName = :$partitionAlias"
                expressionAttributeValues = attrValues
            }

            getClient().use { ddb ->
                val response = ddb.query(request)
                println("count: ${response.count}")
                response.items?.forEach { item ->
                    item.forEach { map ->
                        println("key: ${map.key} / value: ${map.value}")
                    }
                    println("--------------------")
                }
            }
        }
    }

    fun filterForQuery(
        filterKeyAlias: String,
        filterKey: String,
        filterKeyValAlias: String,
        filterKeyVal: String,
        partitionAlias: String,
        partitionKeyName: String,
        partitionKeyVal: String,
    ) {
        runBlocking {
            val attrNameAlias = mutableMapOf<String, String>()
            attrNameAlias["#${filterKeyAlias}"] = filterKey

            val attrValues = mutableMapOf<String, AttributeValue>()
            attrValues[":$partitionAlias"] = AttributeValue.S(partitionKeyVal)
            attrValues[":$filterKeyValAlias"] = AttributeValue.S(filterKeyVal)

            val request = QueryRequest {
                tableName = TABLE_NAME
                keyConditionExpression = "$partitionKeyName = :$partitionAlias"
                filterExpression = "#${filterKeyAlias} = :$filterKeyValAlias"
                expressionAttributeNames = attrNameAlias
                expressionAttributeValues = attrValues
            }

            getClient().use { ddb ->
                val response = ddb.query(request)
                println("count: ${response.count}")
                response.items?.forEach { item ->
                    item.forEach { map ->
                        println("key: ${map.key} / value: ${map.value}")
                    }
                    println("--------------------")
                }
            }
        }
    }

    private fun getClient() = DynamoDbClient {
        region = regions
        credentialsProvider = EnvironmentCredentialsProvider()
        endpointUrl = Url.parse(endpoint)
    }

}