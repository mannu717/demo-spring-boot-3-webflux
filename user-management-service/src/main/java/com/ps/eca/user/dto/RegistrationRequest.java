package com.ps.eca.user.dto;

import com.ps.eca.user.model.enums.UserRole;
import com.ps.eca.user.util.validator.ValidUserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegistrationRequest {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Mobile is required")
    private String mobile;

    @ValidUserRole
    private UserRole role;
}
