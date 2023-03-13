package org.example.common.dto.user;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.NotEmpty;

@Value
@Jacksonized
@Builder
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class CreateUserDto {

    @NotEmpty(message = "User login is empty")
    String login;
    @NotEmpty(message = "User password is empty")
    String password;
    @NotEmpty(message = "User role is empty")
    String role;
}
