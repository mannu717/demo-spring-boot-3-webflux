package com.ps.eca.user.service;

import com.ps.eca.user.dto.RegistrationRequest;
import com.ps.eca.user.exception.UserCreationException;
import com.ps.eca.user.exception.UserNotFoundException;
import com.ps.eca.user.model.User;

public interface UserService {

    User registerUser(RegistrationRequest request) throws UserCreationException;

    User findByEmail(String email) throws UserNotFoundException;

    void throwExceptionIfExist(String email) throws UserNotFoundException;

    void throwExceptionIfNotExist(String email) throws UserNotFoundException;
}

