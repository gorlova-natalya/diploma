package org.example.common.dto.user;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.NotEmpty;

@Data
@Jacksonized
@Builder
@RequiredArgsConstructor
public class CreateUserDto {

    @NotEmpty(message = "User login is empty")
    String login;
    @NotEmpty(message = "User password is empty")
    String password;
    @NotEmpty(message = "User role is empty")
    String role;

    public CreateUserDto(String login, String password, String role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }
}
