package com.ps.eca.apartment.service.impl;

import com.ps.eca.apartment.dto.ApartmentRequestDto;
import com.ps.eca.apartment.dto.ApartmentResponseDto;
import com.ps.eca.apartment.mapper.ApartmentMapper;
import com.ps.eca.apartment.model.Apartment;
import com.ps.eca.apartment.repository.ApartmentRepository;
import com.ps.eca.apartment.service.ApartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Qualifier("apartmentServiceImpl")
public class ApartmentServiceImpl implements ApartmentService {

    private final ApartmentRepository apartmentRepository;
    private final ApartmentMapper apartmentMapper;

    @Autowired
    public ApartmentServiceImpl(ApartmentRepository apartmentRepository, ApartmentMapper apartmentMapper) {
        this.apartmentRepository = apartmentRepository;
        this.apartmentMapper = apartmentMapper;
    }

    @Override
    public ApartmentResponseDto createApartment(ApartmentRequestDto apartmentDto) {
        Apartment apartment = apartmentMapper.toApartment(apartmentDto);
        apartment = apartmentRepository.save(apartment);
        return apartmentMapper.toApartmentResponseDto(apartment);
    }

    @Override
    public ApartmentResponseDto updateApartment(String apartmentId, ApartmentRequestDto updatedApartmentRequestDto) {
        Apartment apartment = getApartmentById(apartmentId);

        apartment.setAmenities(updatedApartmentRequestDto.getAmenities());
        Apartment updatedApartment = apartmentRepository.save(apartment);
        return apartmentMapper.toApartmentResponseDto(updatedApartment);
    }

    @Override
    public void deleteApartment(String apartmentId) {
        Apartment apartment = getApartmentById(apartmentId);
        apartmentRepository.delete(apartment);
    }

    @Override
    public ApartmentResponseDto getApartmentDetails(String apartmentId) {
        Apartment apartment = getApartmentById(apartmentId);
        return apartmentMapper.toApartmentResponseDto(apartment);
    }

    private Apartment getApartmentById(String apartmentId) {
        return apartmentRepository.findById(apartmentId).orElseThrow(() -> new RuntimeException("Apartment by given id not found"));
    }

    @Override
    public List<ApartmentResponseDto> getAllApartments() {
        return StreamSupport.stream(apartmentRepository.findAll().spliterator(), true).map(apartmentMapper::toApartmentResponseDto).collect(Collectors.toList());
    }
}

