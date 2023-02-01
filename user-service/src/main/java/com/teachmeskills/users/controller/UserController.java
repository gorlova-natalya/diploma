package com.teachmeskills.users.controller;

import com.teachmeskills.users.dto.AppUserDto;
import com.teachmeskills.users.dto.Converter;
import com.teachmeskills.users.dto.CreateUserDto;
import com.teachmeskills.users.dto.PageDto;
import com.teachmeskills.users.dto.UsersListDto;
import com.teachmeskills.users.dto.VerifyResultDto;
import com.teachmeskills.users.dto.VerifyUserDto;
import com.teachmeskills.users.facade.UserFacade;
import com.teachmeskills.users.model.User;
import com.teachmeskills.users.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;
    private final Converter converter;
    private final UserFacade userFacade;

    @GetMapping("/me")
    protected AppUserDto getUser(String login) {
        User user = userService.getUser(login);
        return AppUserDto.builder().id(user.getId()).login(user.getLogin()).
                password(user.getPassword()).role(user.getRole().getName()).build();
    }

    @GetMapping
    protected UsersListDto getUsers(@RequestBody final PageDto dto) {
        final Page<User> page = userService.findPaginatedUsers(dto.getPageNo(), dto.getPageSize());
        List<User> listUsers = page.getContent();
        return UsersListDto.builder().listUsers(converter.toDto(listUsers)).
                totalPages(page.getTotalPages()).totalElements(page.getTotalElements()).build();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    protected AppUserDto createUser(@Valid @RequestBody final CreateUserDto dto) {
        final User user = userFacade.createUser(dto.getLogin(), dto.getPassword(), dto.getRole());
        log.info("User {} registered", dto.getLogin());
        return AppUserDto.builder().login(user.getLogin()).password(user.getPassword()).role(user.getRole().getName())
                .build();
    }

    @PostMapping("/verify")
    public VerifyResultDto verifyUser(@RequestBody final VerifyUserDto request) {
        final boolean isValid = userService.verify(request.getLogin(), request.getPassword());
        return VerifyResultDto.builder()
                .valid(isValid)
                .build();
    }
}
