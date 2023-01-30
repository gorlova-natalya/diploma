package com.teachmeskills.users.facade;

import com.teachmeskills.users.model.User;
import com.teachmeskills.users.service.RoleService;
import com.teachmeskills.users.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserFacade {

    private final UserService userService;
    private final RoleService roleService;

    public User createUser(String login, String password, String role) {
        return userService.createUser(login, password, roleService.getRoleByName(role));
    }
}
