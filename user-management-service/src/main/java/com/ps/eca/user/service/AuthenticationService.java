package com.ps.eca.user.service;

import com.ps.eca.user.dto.LoginRequest;
import com.ps.eca.user.dto.LoginResponse;
import com.ps.eca.user.dto.OTPRequest;
import com.ps.eca.user.dto.OTPResponse;
import com.ps.eca.user.exception.LoginException;
import com.ps.eca.user.exception.OTPException;
import com.ps.eca.user.exception.UserCreationException;

public interface AuthenticationService {
    OTPResponse generateOTP(OTPRequest otpRequest) throws UserCreationException;

    LoginResponse userLogin(LoginRequest request) throws OTPException, LoginException;

    boolean validateToken(String token);
}
