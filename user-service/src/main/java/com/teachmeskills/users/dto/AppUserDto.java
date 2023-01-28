package com.teachmeskills.users.dto;

import com.teachmeskills.users.model.Role;
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
    Role role;
}
