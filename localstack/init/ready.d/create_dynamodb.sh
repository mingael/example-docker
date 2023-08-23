#!/bin/bash

echo $(aws dynamodb create-table \
--endpoint-url http://localhost:4566 \
--table-name ad_event \
--attribute-definitions \
 AttributeName=id,AttributeType=S \
--key-schema \
 AttributeName=id,KeyType=HASH \
--provisioned-throughput ReadCapacityUnits=5,WriteCapacityUnits=5)

echo $(aws dynamodb create-table \
--endpoint-url http://localhost:4566 \
--table-name button_event \
--attribute-definitions \
 AttributeName=id,AttributeType=N \
 AttributeName=username,AttributeType=S \
--key-schema \
 AttributeName=id,KeyType=HASH \
 AttributeName=username,KeyType=RANGE \
--provisioned-throughput ReadCapacityUnits=5,WriteCapacityUnits=5)

echo $(aws dynamodb create-table \
--endpoint-url http://localhost:4566 \
--table-name click_event \
--attribute-definitions \
 AttributeName=id,AttributeType=N \
 AttributeName=reg_dtm,AttributeType=S \
--key-schema \
 AttributeName=id,KeyType=HASH \
 AttributeName=reg_dtm,KeyType=RANGE \
--provisioned-throughput ReadCapacityUnits=5,WriteCapacityUnits=5)

echo $(aws dynamodb create-table \
--endpoint-url http://localhost:4566 \
--table-name scroll_event \
--attribute-definitions \
 AttributeName=id,AttributeType=S \
--key-schema \
 AttributeName=id,KeyType=HASH \
--provisioned-throughput ReadCapacityUnits=5,WriteCapacityUnits=5)
