package com.teachmeskills.security.client;

import com.teachmeskills.security.client.dto.AppUserDto;
import com.teachmeskills.security.client.dto.CreateUserDto;
import com.teachmeskills.security.client.dto.PageDto;
import com.teachmeskills.security.client.dto.UsersListDto;
import com.teachmeskills.security.client.dto.VerifyResultDto;
import com.teachmeskills.security.client.dto.VerifyUserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "domain", url = "${services.user.url}/api/v1/users")
public interface UserClient {

    @RequestMapping(method = RequestMethod.GET, value = "/{login}")
    AppUserDto getUser(@PathVariable(name = "login") final String login);

    @RequestMapping(method = RequestMethod.GET)
    UsersListDto getUsers(final PageDto pageDto);

    @RequestMapping(method = RequestMethod.POST, value = "/verify")
    VerifyResultDto verifyUser(final VerifyUserDto requests);

    @RequestMapping(method = RequestMethod.POST)
    AppUserDto saveUser(final CreateUserDto request);
}
