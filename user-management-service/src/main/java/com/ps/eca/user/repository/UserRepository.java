package com.ps.eca.user.repository;

import com.ps.eca.user.model.User;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

@EnableScan
public interface UserRepository extends CrudRepository<User, String> {
    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);
}