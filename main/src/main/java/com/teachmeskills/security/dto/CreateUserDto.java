package com.teachmeskills.security.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.jackson.Jacksonized;

@Data
@Jacksonized
@RequiredArgsConstructor
public class CreateUserDto {

    String login;
    String password;
    String role;
}
