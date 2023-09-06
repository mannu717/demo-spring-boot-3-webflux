package com.ps.eca.apartment.service.impl;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.Condition;
import com.ps.eca.apartment.dto.ApartmentResponseDto;
import com.ps.eca.apartment.mapper.ApartmentMapper;
import com.ps.eca.apartment.model.Apartment;
import com.ps.eca.apartment.service.ApartmentSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApartmentSearchServiceImpl implements ApartmentSearchService {

    private final DynamoDBMapper dynamoDBMapper;
    private final ApartmentMapper apartmentMapper;

    @Autowired
    public ApartmentSearchServiceImpl(DynamoDBMapper dynamoDBMapper, ApartmentMapper apartmentMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
        this.apartmentMapper = apartmentMapper;
    }

    @Override
    public List<ApartmentResponseDto> searchApartments(String name, Integer bedrooms, Integer bathrooms, Double priceGreaterThan, Double priceLessThan, Boolean available) {
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        if (name != null) {
            scanExpression.addFilterCondition("name", new Condition().withComparisonOperator(ComparisonOperator.EQ).withAttributeValueList(new AttributeValue(name)));
        }

        if (bedrooms != null) {
            scanExpression.addFilterCondition("bedrooms", new Condition().withComparisonOperator(ComparisonOperator.EQ).withAttributeValueList(new AttributeValue().withN(String.valueOf(bedrooms))));
        }

        if (bathrooms != null) {
            scanExpression.addFilterCondition("bathrooms", new Condition().withComparisonOperator(ComparisonOperator.EQ).withAttributeValueList(new AttributeValue().withN(String.valueOf(bathrooms))));
        }

        if (available != null) {
            scanExpression.addFilterCondition("available", new Condition().withComparisonOperator(ComparisonOperator.EQ).withAttributeValueList(new AttributeValue().withBOOL(available)));
        }

        if (priceGreaterThan != null && priceLessThan != null) {
            scanExpression.addFilterCondition("price", new Condition().withComparisonOperator(ComparisonOperator.BETWEEN).withAttributeValueList(
                    new AttributeValue().withN(String.valueOf(priceGreaterThan)),
                    new AttributeValue().withN(String.valueOf(priceLessThan))
            ));
        } else if (priceGreaterThan != null) {
            scanExpression.addFilterCondition("price", new Condition().withComparisonOperator(ComparisonOperator.GT).withAttributeValueList(new AttributeValue().withN(String.valueOf(priceGreaterThan))));
        } else if (priceLessThan != null) {
            scanExpression.addFilterCondition("price", new Condition().withComparisonOperator(ComparisonOperator.LT).withAttributeValueList(new AttributeValue().withN(String.valueOf(priceLessThan))));
        }

        return dynamoDBMapper.scan(Apartment.class, scanExpression).stream().map(apartment -> apartmentMapper.toApartmentResponseDto(apartment)).collect(Collectors.toList());
    }
}
