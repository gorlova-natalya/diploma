package com.teachmeskills.users.dto;

import com.teachmeskills.users.model.Role;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Jacksonized
@Builder
public class CreateUserDto {

    String login;
    String password;
    Role role;
}
