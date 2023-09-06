package com.ps.eca.user.util.converter;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.ps.eca.user.model.enums.UserRole;

public class UserRoleConverter implements DynamoDBTypeConverter<String, UserRole> {

    @Override
    public String convert(UserRole enumValue) {
        return enumValue.toString(); // Convert enum to string
    }

    @Override
    public UserRole unconvert(String stringValue) {
        return UserRole.valueOf(stringValue); // Convert string to enum
    }
}

