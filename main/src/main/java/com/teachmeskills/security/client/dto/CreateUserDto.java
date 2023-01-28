package com.teachmeskills.security.client.dto;

import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotEmpty;

@Value
@Builder
public class CreateUserDto {

    @NotEmpty
    String login;
    @NotEmpty
    String password;
    @NotEmpty
    String role;
}
