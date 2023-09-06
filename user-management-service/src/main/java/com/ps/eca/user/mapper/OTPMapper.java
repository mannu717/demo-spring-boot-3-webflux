package com.ps.eca.user.mapper;

import com.ps.eca.user.dto.OTPResponse;
import com.ps.eca.user.model.Otp;
import org.mapstruct.Mapper;

import java.time.Instant;

@Mapper(componentModel = "spring")
public interface OTPMapper {

    OTPResponse toOTPResponse(Otp otp);

    default Instant toInstant(long epochMilli) {
        return Instant.ofEpochMilli(epochMilli);
    }
}
