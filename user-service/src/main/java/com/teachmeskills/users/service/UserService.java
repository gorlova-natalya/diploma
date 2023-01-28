package com.teachmeskills.users.service;

import com.teachmeskills.users.model.Role;
import com.teachmeskills.users.model.User;
import com.teachmeskills.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final HashPassword hashPassword;

    public List<User> findUsers() {
        return userRepository.findAll();
    }

    private String hashingPassword(String password) {
        return hashPassword.hashingPassword(password);
    }

    public Page<User> findPaginatedUsers(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return userRepository.findAll(pageable);
    }

    public User createUser(String login, String password, Role role) {
        log.info("Creating user with login {}", login);
        if (password.isEmpty()) {
            log.info("User password is empty");
            throw new RuntimeException("User password is empty");
        }
        final User user = new User(login, hashingPassword(password), role);
        userRepository.save(user);
        log.info("User with login {} successfully create", login);
        return user;
    }

    public boolean verify(String login, String password) {
        Optional<User> user = userRepository.getUserByLogin(login);
        return user.filter(value -> hashPassword.validatePassword(password, value.getPassword())).isPresent();
    }
}


