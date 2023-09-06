package com.ps.eca.user.dto;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class OTPResponse {

    String otp;
    Instant expirationTime;
}
