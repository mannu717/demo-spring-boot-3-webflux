package com.ps.eca.apartment.service.impl;

import com.ps.eca.apartment.dto.ApartmentRequestDto;
import com.ps.eca.apartment.dto.ApartmentResponseDto;
import com.ps.eca.apartment.service.ApartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("apartmentProxyServiceImpl")
public class ApartmentProxyServiceImpl implements ApartmentService {

    private final ApartmentService apartmentService;

    @Autowired
    public ApartmentProxyServiceImpl(@Qualifier("apartmentServiceImpl") ApartmentService apartmentService) {
        this.apartmentService = apartmentService;
    }

    @Override
    @Caching(put = {@CachePut(value = "apartment_cache", key = "#result.id", condition = "#result != null"),}, cacheable = {@Cacheable(value = "apartment_cache", key = "#result.id", condition = "#result != null"),})
    public ApartmentResponseDto createApartment(ApartmentRequestDto apartmentDto) {
        return apartmentService.createApartment(apartmentDto);
    }

    @Override
    @CachePut(value = "apartment_cache", key = "#apartmentId")
    public ApartmentResponseDto updateApartment(String apartmentId, ApartmentRequestDto updatedApartmentRequestDto) {
        return apartmentService.updateApartment(apartmentId, updatedApartmentRequestDto);
    }

    @Override
    @CacheEvict(value = "apartment_cache", key = "#apartmentId")
    public void deleteApartment(String apartmentId) {
        apartmentService.deleteApartment(apartmentId);
    }

    @Override
    @Cacheable(value = "apartment_cache", key = "#apartmentId")
    public ApartmentResponseDto getApartmentDetails(String apartmentId) {
        return apartmentService.getApartmentDetails(apartmentId);
    }

    @Override
//    @Cacheable(value = "apartment_cache", key = "all_records")
    /**
     * don't use cash in this
     * 1st issue is cash size will increase
     * 2nd issue is we were not able to store record based on id so it create problem during delete, eviction won't work
     */
    public List<ApartmentResponseDto> getAllApartments() {
        return apartmentService.getAllApartments();
    }
}

