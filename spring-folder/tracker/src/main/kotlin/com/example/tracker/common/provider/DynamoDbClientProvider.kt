package com.example.tracker.common.provider

import aws.sdk.kotlin.runtime.auth.credentials.EnvironmentCredentialsProvider
import aws.sdk.kotlin.services.dynamodb.DynamoDbClient
import aws.smithy.kotlin.runtime.net.Url
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class DynamoDbClientProvider(
    @Value("\${cloud.aws.dynamodb.endpoint}") private val endpoint: String,
    @Value("\${cloud.aws.region.static}") private val regions: String
) {
    fun client() = DynamoDbClient {
        region = regions
        endpointUrl = Url.parse(endpoint)
        credentialsProvider = EnvironmentCredentialsProvider()
    }
}