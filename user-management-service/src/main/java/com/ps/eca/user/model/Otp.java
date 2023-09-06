package com.ps.eca.user.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamoDBTable(tableName = "Otp")
public class Otp {

    @DynamoDBHashKey(attributeName = "emailId")
    private String emailId;

    @DynamoDBAttribute(attributeName = "otp")
    private String otp;

    @DynamoDBAttribute(attributeName = "expirationTime")
    private long expirationTime;
}
