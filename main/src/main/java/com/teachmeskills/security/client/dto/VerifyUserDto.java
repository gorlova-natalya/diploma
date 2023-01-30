package com.teachmeskills.security.client.dto;

import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotEmpty;

@Value
@Builder
public class VerifyUserDto {

    String login;
    String password;
}
