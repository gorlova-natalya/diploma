package com.teachmeskills.security.dto;


import com.teachmeskills.security.validator.ValidUser;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@ValidUser
public class AuthUserDto {

    @NotEmpty(message = "Login cannot be empty")
    private String login;
    @NotEmpty(message = "Password cannot be empty")
    private String password;
    private String confirmationPassword;
}
