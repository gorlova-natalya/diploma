package com.teachmeskills.security.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UserDtoRest {
    long id;
    String login;
    String password;
}
