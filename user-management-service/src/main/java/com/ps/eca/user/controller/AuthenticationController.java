package com.ps.eca.user.controller;

import com.ps.eca.user.dto.LoginRequest;
import com.ps.eca.user.dto.LoginResponse;
import com.ps.eca.user.dto.OTPRequest;
import com.ps.eca.user.dto.OTPResponse;
import com.ps.eca.user.exception.LoginException;
import com.ps.eca.user.exception.OTPException;
import com.ps.eca.user.exception.UserCreationException;
import com.ps.eca.user.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/otp")
    public ResponseEntity<OTPResponse> requestOTP(@RequestBody OTPRequest otpRequest) {
        try {
            OTPResponse otpResponse = authenticationService.generateOTP(otpRequest);
            return ResponseEntity.ok(otpResponse);
        } catch (UserCreationException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> requestLogin(@RequestBody LoginRequest request) {
        try {
            LoginResponse response = authenticationService.userLogin(request);
            return ResponseEntity.ok(response);
        } catch (OTPException e) {
//            throw new RuntimeException(e);
        } catch (LoginException e) {
//            throw new RuntimeException(e);
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/validate-token")
    public ResponseEntity<String> requestTokenValidation(@RequestParam("token") String token) {
        boolean isValid = authenticationService.validateToken(token);
        if (isValid) {
            return ResponseEntity.ok("VALID");
        } else {
            return ResponseEntity.ok("INVALID");
        }
    }
}
