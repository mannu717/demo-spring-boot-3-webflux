package com.ps.eca.user.model;

import com.ps.eca.user.model.enums.UserRole;
import com.ps.eca.user.util.validator.ValidUserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateUserRequest {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @ValidUserRole
    private UserRole role;
}