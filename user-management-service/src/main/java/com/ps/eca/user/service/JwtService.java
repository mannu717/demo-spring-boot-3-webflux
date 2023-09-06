package com.ps.eca.user.service;

import com.ps.eca.user.dto.TokenDto;

public interface JwtService {

    TokenDto generateToken(String subject);

    boolean validateToken(String token);
}
