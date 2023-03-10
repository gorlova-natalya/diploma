package com.teachmeskills.security.client;

import com.teachmeskills.security.client.dto.AppUserDto;
import com.teachmeskills.security.dto.CreateUserDto;
import com.teachmeskills.security.client.dto.PageDto;
import com.teachmeskills.security.client.dto.UsersListDto;
import com.teachmeskills.security.client.dto.VerifyResultDto;
import com.teachmeskills.security.client.dto.VerifyUserDto;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "domain", url = "${services.user.url}/api/v1/users")
public interface UserClient {

    @RequestMapping(method = RequestMethod.GET, value = "/me")
    @Headers(value = "Content-Type: application/json")
    AppUserDto getUser(final String login);

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
