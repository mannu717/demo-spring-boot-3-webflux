package com.ps.eca.apartment.controller;

import com.ps.eca.apartment.dto.ApartmentResponseDto;
import com.ps.eca.apartment.service.ApartmentSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/apartments")
public class ApartmentSearchController {
    /**
     * Search Apartments: GET /apartments/search
     */

    private final ApartmentSearchService apartmentSearchService;

    @Autowired
    public ApartmentSearchController(ApartmentSearchService apartmentSearchService) {
        this.apartmentSearchService = apartmentSearchService;
    }

    @GetMapping("/search")
    public List<ApartmentResponseDto> searchApartments(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer bedrooms,
            @RequestParam(required = false) Integer bathrooms,
            @RequestParam(required = false) Double priceGreaterThan,
            @RequestParam(required = false) Double priceLessThan,
            @RequestParam(required = false) Boolean available
    ) {
        return apartmentSearchService.searchApartments(name, bedrooms, bathrooms, priceGreaterThan, priceLessThan, available);
    }
}
