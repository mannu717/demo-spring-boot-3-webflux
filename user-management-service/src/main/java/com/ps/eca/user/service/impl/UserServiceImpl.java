package com.ps.eca.user.service.impl;

import com.ps.eca.user.dto.RegistrationRequest;
import com.ps.eca.user.exception.UserAlreadyExistException;
import com.ps.eca.user.exception.UserCreationException;
import com.ps.eca.user.exception.UserNotFoundException;
import com.ps.eca.user.mapper.UserMapper;
import com.ps.eca.user.model.User;
import com.ps.eca.user.repository.UserRepository;
import com.ps.eca.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public User registerUser(RegistrationRequest request) throws UserCreationException {
        // Validate if email already exists
        throwExceptionIfExist(request.getEmail());

        // Create and save user
        User user = userMapper.toUser(request);
        return userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) throws UserNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("User not found for given email."));
    }

    private boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public void throwExceptionIfExist(String email) {
        if (existsByEmail(email)) {
            throw new UserAlreadyExistException("Email already exists");
        }
    }

    public void throwExceptionIfNotExist(String email) {
        if (!existsByEmail(email)) {
            throw new UserNotFoundException("Email not exists");
        }
    }
}
