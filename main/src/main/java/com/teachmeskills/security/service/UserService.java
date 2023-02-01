package com.teachmeskills.security.service;

import com.teachmeskills.security.client.UserClient;
import com.teachmeskills.security.client.dto.AppUserDto;
import com.teachmeskills.security.dto.CreateUserDto;
import com.teachmeskills.security.client.dto.PageDto;
import com.teachmeskills.security.client.dto.UsersListDto;
import com.teachmeskills.security.client.dto.VerifyResultDto;
import com.teachmeskills.security.client.dto.VerifyUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

    public AppUserDto createUser(final CreateUserDto user) {
        final CreateUserDto request = new CreateUserDto();
                request.setLogin(user.getLogin());
                request.setPassword(user.getPassword());
                request.setRole(user.getRole());
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
