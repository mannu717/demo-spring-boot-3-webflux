package com.ps.eca.apartment.controller;

import com.ps.eca.apartment.dto.ApartmentRequestDto;
import com.ps.eca.apartment.dto.ApartmentResponseDto;
import com.ps.eca.apartment.model.Apartment;
import com.ps.eca.apartment.service.ApartmentService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apartments")
public class ApartmentController {

    /**
     * Create Apartment: POST /apartments
     * Update Apartment: PUT /apartments/{apartmentId}
     * Delete Apartment: DELETE /apartments/{apartmentId}
     * Get Apartment Details: GET /apartments/{apartmentId}
     * List Apartments: GET /apartments     *
     */
    private final ApartmentService apartmentService;

    @Autowired
    public ApartmentController(@Qualifier("apartmentProxyServiceImpl") ApartmentService apartmentService) {
        this.apartmentService = apartmentService;
    }


    @PostMapping
    @Operation(description = "Create Apartment")
    public ResponseEntity<ApartmentResponseDto> createApartment(@RequestBody ApartmentRequestDto apartmentDto) {
        ApartmentResponseDto apartmentResponseDto = apartmentService.createApartment(apartmentDto);
        return ResponseEntity.ok(apartmentResponseDto);
    }

    @PutMapping("/{apartmentId}")
    @Operation(description = "Update Apartment")
    public ResponseEntity<ApartmentResponseDto> updateApartment(
            @PathVariable String apartmentId,
            @RequestBody ApartmentRequestDto updatedApartment
    ) {
        try {
            ApartmentResponseDto apartmentResponseDto = apartmentService.updateApartment(apartmentId, updatedApartment);
            return ResponseEntity.ok(apartmentResponseDto);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{apartmentId}")
    @Operation(description = "Delete Apartment")
    public ResponseEntity<Void> deleteApartment(@PathVariable String apartmentId) {
        try {
            apartmentService.deleteApartment(apartmentId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{apartmentId}")
    @Operation(description = "Get Apartment Details")
    public ResponseEntity<ApartmentResponseDto> getApartmentDetails(@PathVariable String apartmentId) {
        try {
            ApartmentResponseDto apartmentResponseDto = apartmentService.getApartmentDetails(apartmentId);
            return ResponseEntity.ok(apartmentResponseDto);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    @Operation(description = "List Apartments")
    public ResponseEntity<List<ApartmentResponseDto>> getAllApartments() {
        try {
            return ResponseEntity.ok(apartmentService.getAllApartments());
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}

