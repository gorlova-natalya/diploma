package com.teachmeskills.users.facade;

import com.teachmeskills.users.model.User;
import com.teachmeskills.users.service.RoleService;
import com.teachmeskills.users.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class UserFacade {

    private final UserService userService;
    private final RoleService roleService;

    public User createUser(String login, String password, String role) {
        return userService.createUser(login, password, roleService.getRoleByName(role));
    }

    public User getUser(String login) {
        return userService.getUser(login).stream().findFirst().orElse(null);
    }

    public List<User> findUsers() {
        return userService.findUsers();
    }

    public Page<User> findPaginatedUsers(int pageNo, int pageSize) {
        return userService.findPaginatedUsers(pageNo, pageSize);
    }

    public boolean verify(String login, String password) {
        return userService.verify(login, password);
    }
}
