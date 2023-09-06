package com.ps.eca.user.service.impl;

import com.ps.eca.user.dto.*;
import com.ps.eca.user.exception.LoginException;
import com.ps.eca.user.exception.OTPException;
import com.ps.eca.user.exception.UserCreationException;
import com.ps.eca.user.mapper.OTPMapper;
import com.ps.eca.user.model.Otp;
import com.ps.eca.user.model.User;
import com.ps.eca.user.service.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final OTPService otpService;
    private final OTPMapper otpMapper;
    private final UserService userService;
    private final JwtService jwtService;

    private final CommunicationService emailCommunicationService;
    private final CommunicationService smsCommunicationService;

    public AuthenticationServiceImpl(OTPService otpService, OTPMapper otpMapper, UserService userService, JwtService jwtService,
                                     @Qualifier("emailCommunicationService") CommunicationService emailCommunicationService,
                                     @Qualifier("smsCommunicationService") CommunicationService smsCommunicationService) {
        this.otpService = otpService;
        this.otpMapper = otpMapper;
        this.userService = userService;
        this.jwtService = jwtService;
        this.emailCommunicationService = emailCommunicationService;
        this.smsCommunicationService = smsCommunicationService;
    }

    @Override
    public OTPResponse generateOTP(OTPRequest request) throws UserCreationException {
        String email = request.getEmail();
        userService.throwExceptionIfNotExist(email);

        try {
            Otp otp = otpService.findByEmailId(email);
            if (otpService.validateOtp(email, otp.getOtp())) {
                return otpMapper.toOTPResponse(otp);
            } else {
                otpService.findByEmailId(email);
            }
        } catch (OTPException otpException) {
        }

        String otpStr = otpService.generateOTP();

        long currentTimeMillis = System.currentTimeMillis();

        //save otp into db with expiry time and ttl enabled
        Otp otp = Otp.builder()
                .emailId(email)
                .otp(otpStr)
                .expirationTime(currentTimeMillis + 5 * 60 * 1000)
                .build();
        otp = otpService.save(otp);

        return otpMapper.toOTPResponse(otp);
    }

    @Override
    public LoginResponse userLogin(LoginRequest request) throws OTPException, LoginException {
        String email = request.getEmail();
        userService.throwExceptionIfNotExist(email);
        User user = userService.findByEmail(email);

        String otp = request.getOtp();
        if (!otpService.validateOtp(email, otp)) {
            throw new LoginException("Invalid OTP");
        }

        TokenDto tokenDto = jwtService.generateToken(otp);

        //Send otp to user via SMS and EMail
        emailCommunicationService.sendMessage(user.getEmail(), otp);
        smsCommunicationService.sendMessage(user.getMobile(), otp);

        return LoginResponse.builder()
                .token(tokenDto.getToken())
                .expirationTime(tokenDto.getExpiration().toInstant())
                .build();
    }

    @Override
    public boolean validateToken(String token) {
        return jwtService.validateToken(token);
    }
}
