package com.ps.eca.apartment.mapper;

import com.ps.eca.apartment.dto.ApartmentRequestDto;
import com.ps.eca.apartment.dto.ApartmentResponseDto;
import com.ps.eca.apartment.model.Apartment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ApartmentMapper {

    Apartment toApartment(ApartmentRequestDto apartmentRequestDto);

    ApartmentResponseDto toApartmentResponseDto(Apartment apartment);
}
