package com.teachmeskills.security.service;

import com.teachmeskills.security.client.UserClient;
import com.teachmeskills.security.client.dto.AppUserDto;
import com.teachmeskills.security.client.dto.CreateUserDto;
import com.teachmeskills.security.client.dto.PageDto;
import com.teachmeskills.security.client.dto.UsersListDto;
import com.teachmeskills.security.client.dto.VerifyResultDto;
import com.teachmeskills.security.client.dto.VerifyUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserClient userClient;

    public AppUserDto getUser(final String login) {
        return userClient.getUser(login);
    }

    public UsersListDto getUsers(int pageNo, int pageSize) {
        PageDto pageDto = PageDto.builder().pageNo(pageNo).pageSize(pageSize).build();

        return userClient.getUsers(pageDto);
    }


    public AppUserDto saveUser(final AppUserDto user) {
        final CreateUserDto request = CreateUserDto.builder()
                .login(user.getLogin())
                .password(user.getPassword())
                .role(user.getRole())
                .build();

        return userClient.saveUser(request);
    }

    public VerifyResultDto verifyUser(final String login, final String password) {
        final VerifyUserDto request = VerifyUserDto.builder()
                .login(login)
                .password(password)
                .build();

        return userClient.verifyUser(request);
    }
}
