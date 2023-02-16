package org.example.common.dto.user;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@Data
@Valid
public class UserDto {

    @NotEmpty(message = "Login must not be empty")
    private String login;

    @NotEmpty(message = "Password must not be empty")
    private String password;
}
