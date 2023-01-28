package com.teachmeskills.security.dto;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Builder
@Value
public class CredentialsDto {

    String login;
    String password;
}
