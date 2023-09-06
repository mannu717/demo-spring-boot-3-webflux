package com.ps.eca.user.util.validator;

import com.ps.eca.user.model.enums.UserRole;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidUserRoleValidator implements ConstraintValidator<ValidUserRole, UserRole> {

    @Override
    public void initialize(ValidUserRole constraintAnnotation) {
    }

    @Override
    public boolean isValid(UserRole value, ConstraintValidatorContext context) {
        return value != null; // You can add more complex validation logic here if needed
    }
}

