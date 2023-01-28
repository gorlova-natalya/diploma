package com.teachmeskills.security.session;

import lombok.Data;

@Data
public class AuthContext {

    private Long loggedInUserId;
    private String username;
}
