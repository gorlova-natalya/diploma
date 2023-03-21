package com.teachmeskills.security.client;

import org.example.common.dto.user.VerifyUserDto;
import feign.Headers;
import org.example.common.dto.user.AppUserDto;
import org.example.common.dto.user.CreateUserDto;
import org.example.common.dto.user.PageDto;
import org.example.common.dto.user.UsersListDto;
import org.example.common.dto.user.VerifyResultDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "domain", url = "${services.user.url}/api/v1/users")
public interface UserClient {

    @RequestMapping(method = RequestMethod.GET, value = "/{login}")
    @Headers(value = "Content-Type: application/json")
    AppUserDto getUser(@PathVariable("login") final String login);

    @RequestMapping(method = RequestMethod.GET)
    @Headers(value = "Content-Type: application/json")
    UsersListDto getUsers(final PageDto pageDto);

    @RequestMapping(method = RequestMethod.GET, value = "/all")
    @Headers(value = "Content-Type: application/json")
    List<AppUserDto> getAllUsers();

    @RequestMapping(method = RequestMethod.POST, value = "/verify")
    VerifyResultDto verifyUser(final VerifyUserDto request);

    @RequestMapping(method = RequestMethod.POST)
    AppUserDto createUser(final CreateUserDto request);
}
