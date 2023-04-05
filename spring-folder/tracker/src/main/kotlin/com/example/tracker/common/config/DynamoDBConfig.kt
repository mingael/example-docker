package com.example.tracker.common.config

import com.amazonaws.auth.AWSCredentialsProvider
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain
import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary

@Configuration
@EnableDynamoDBRepositories(basePackages = ["com.example.tracker"])
class DynamoDBConfig(
    @Value("\${cloud.aws.dynamodb.endpoint}") private val endpoint: String,
    @Value("\${cloud.aws.region.static}") private val region: String
) {

    @Bean
    @Primary
    fun dynamoDBMapper(amazonDynamoDB: AmazonDynamoDB, dynamoDBMapperConfig: DynamoDBMapperConfig): DynamoDBMapper {
        return DynamoDBMapper(amazonDynamoDB, dynamoDBMapperConfig)
    }

    @Bean
    @Primary
    fun awsCredentialsProvider(): AWSCredentialsProvider {
        return DefaultAWSCredentialsProviderChain.getInstance() // 자격 증명을 환경변수, 프로필 파일(~/.aws/credentials) 순으로 탐색
    }

    @Bean
    @Primary
    fun dynamoDBMapperConfig(): DynamoDBMapperConfig {
        return DynamoDBMapperConfig.DEFAULT
    }

    @Bean
    fun amazonDynamoDB(): AmazonDynamoDB {
        return AmazonDynamoDBClientBuilder
            .standard()
            .withEndpointConfiguration(AwsClientBuilder.EndpointConfiguration(endpoint, region))
            .withCredentials(awsCredentialsProvider())
            .build()
    }
}