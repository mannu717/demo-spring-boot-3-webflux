package com.ps.eca.apartment.service;

import com.ps.eca.apartment.dto.ApartmentResponseDto;

import java.util.List;

public interface ApartmentSearchService {
    List<ApartmentResponseDto> searchApartments(String name, Integer bedrooms, Integer bathrooms, Double priceGreaterThan, Double priceLessThan, Boolean available);
}
