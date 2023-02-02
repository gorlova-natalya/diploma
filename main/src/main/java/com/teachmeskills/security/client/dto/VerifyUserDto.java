package com.teachmeskills.security.client.dto;

import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotEmpty;

@Value
@Builder
public class VerifyUserDto {

    @NotEmpty
    String login;
    @NotEmpty
    String password;
}
