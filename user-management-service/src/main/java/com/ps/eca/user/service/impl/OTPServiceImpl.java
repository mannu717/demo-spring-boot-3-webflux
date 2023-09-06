package com.ps.eca.user.service.impl;

import com.ps.eca.user.exception.OTPException;
import com.ps.eca.user.model.Otp;
import com.ps.eca.user.repository.OtpRepository;
import com.ps.eca.user.service.OTPService;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class OTPServiceImpl implements OTPService {

    private static final int OTP_LENGTH = 6;
    private final OtpRepository otpRepository;

    public OTPServiceImpl(OtpRepository otpRepository) {
        this.otpRepository = otpRepository;
    }

    @Override
    public String generateOTP() {
        StringBuilder otp = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < OTP_LENGTH; i++) {
            otp.append(random.nextInt(10));
        }
        return otp.toString();
    }

    @Override
    public Otp save(Otp otp) {
        return otpRepository.save(otp);
    }

    @Override
    public Otp findByEmailId(String emailId) throws OTPException {
        return otpRepository.findById(emailId).orElseThrow(() -> new OTPException("OTP not found for given emailId"));
    }

    @Override
    public void deleteByEmailId(String emailId) {
        deleteByEmailId(emailId);
    }

    @Override
    public boolean validateOtp(String email, String otpStr) throws OTPException {
        Otp otp = findByEmailId(email);
        if (checkIfExpired(otp.getExpirationTime())) {
            otpRepository.delete(otp);
            throw new OTPException("OTP expired");
        }
        if (otp.getOtp().equals(otpStr))
            return Boolean.TRUE;
        return Boolean.FALSE;
    }

    private boolean checkIfExpired(long expirationTime) {
        return System.currentTimeMillis() >= expirationTime;
    }
}
