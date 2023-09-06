package com.ps.eca.user.mapper;

import com.ps.eca.user.dto.RegistrationRequest;
import com.ps.eca.user.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toUser(RegistrationRequest request);
}
