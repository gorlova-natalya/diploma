package com.teachmeskills.users.dto;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.NotEmpty;

@Value
@Jacksonized
@Builder
public class CreateUserDto {

    @NotEmpty(message = "User login is empty")
    String login;
    @NotEmpty(message = "User password is empty")
    String password;
    @NotEmpty(message = "User role is empty")
    String role;
}
