package com.ps.eca.apartment.repository;

import com.ps.eca.apartment.model.Apartment;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface ApartmentRepository extends CrudRepository<Apartment, String> {

}
