package org.example.common.dto.user;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.NotEmpty;

@Value
@Builder
@Jacksonized
public class VerifyUserDto {

    @NotEmpty
    String login;
    @NotEmpty
    String password;
}
