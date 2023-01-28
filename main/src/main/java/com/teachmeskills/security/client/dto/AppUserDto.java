package com.teachmeskills.security.client.dto;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Value
@Builder
public class AppUserDto {

    long id;
    String login;
    String password;
    String role;
}
