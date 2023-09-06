package com.ps.eca.user.repository;

import com.ps.eca.user.model.Otp;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

@EnableScan
public interface OtpRepository extends CrudRepository<Otp, String> {

    Optional<Otp> findByEmailIdAndOtp(String email, String otp);
}
