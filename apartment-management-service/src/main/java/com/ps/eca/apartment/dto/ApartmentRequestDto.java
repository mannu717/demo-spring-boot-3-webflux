package com.ps.eca.apartment.dto;

import lombok.Data;

import java.util.List;

@Data
public class ApartmentRequestDto {

    private String name;
    private String location;
    private String type;
    private String apartmentNumber;
    private String floor;
    private String ownerUserId;
    private List<String> amenities;
    private int age;
    private int bedrooms;
    private int bathrooms;
    private double price;
    private boolean forRent;
    private boolean available;
    private double sizeInSquareFeet;
}
