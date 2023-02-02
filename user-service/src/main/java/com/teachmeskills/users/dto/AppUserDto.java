package com.teachmeskills.users.dto;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class AppUserDto {

    long id;
    String login;
    String password;
    String role;
}
