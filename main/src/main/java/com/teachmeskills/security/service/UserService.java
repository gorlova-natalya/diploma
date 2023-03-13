package com.teachmeskills.security.service;

import com.teachmeskills.security.client.UserClient;
import org.example.common.dto.user.VerifyUserDto;
import lombok.RequiredArgsConstructor;
import org.example.common.dto.user.AppUserDto;
import org.example.common.dto.user.CreateUserDto;
import org.example.common.dto.user.PageDto;
import org.example.common.dto.user.UsersListDto;
import org.example.common.dto.user.VerifyResultDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserClient userClient;

    public AppUserDto getUser(final String login) {
        return userClient.getUser(login);
    }

    public UsersListDto getUsers(final PageDto pageDto) {
        return userClient.getUsers(pageDto);
    }

    public List<AppUserDto> getAllUsers() {
        return userClient.getAllUsers();
    }

    public AppUserDto createUser(final CreateUserDto user) {
        final CreateUserDto request = CreateUserDto.builder()
                .login(user.getLogin())
                .password(user.getPassword())
                .role(user.getRole())
                .build();
        return userClient.createUser(request);
    }

    public VerifyResultDto verifyUser(final String login, final String password) {
        final VerifyUserDto request = VerifyUserDto.builder()
                .login(login)
                .password(password)
                .build();

        return userClient.verifyUser(request);
    }
}
