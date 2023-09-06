package com.ps.eca.user.service;

import com.ps.eca.user.exception.OTPException;
import com.ps.eca.user.model.Otp;

public interface OTPService {
    String generateOTP();

    Otp save(Otp otp);

    Otp findByEmailId(String emailId) throws OTPException;

    void deleteByEmailId(String emailId);

    boolean validateOtp(String email, String otp) throws OTPException;
}
