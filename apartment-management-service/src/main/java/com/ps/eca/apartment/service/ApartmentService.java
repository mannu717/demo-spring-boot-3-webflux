package com.ps.eca.apartment.service;

import com.ps.eca.apartment.dto.ApartmentRequestDto;
import com.ps.eca.apartment.dto.ApartmentResponseDto;
import com.ps.eca.apartment.model.Apartment;

import java.util.List;

public interface ApartmentService {
    ApartmentResponseDto createApartment(ApartmentRequestDto apartmentDto);

    ApartmentResponseDto updateApartment(String apartmentId, ApartmentRequestDto updatedApartment);

    List<ApartmentResponseDto> getAllApartments();

    void deleteApartment(String apartmentId);

    ApartmentResponseDto getApartmentDetails(String apartmentId);
}
