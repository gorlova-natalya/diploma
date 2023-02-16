package com.teachmeskills.users.controller;

import lombok.AllArgsConstructor;
import org.example.common.dto.user.AppUserDto;
import com.teachmeskills.users.converter.Converter;
import org.example.common.dto.user.PageDto;
import org.example.common.dto.user.UsersListDto;
import org.example.common.dto.user.VerifyResultDto;
import com.teachmeskills.users.facade.UserFacade;
import com.teachmeskills.users.model.User;
import com.teachmeskills.users.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.example.common.dto.user.CreateUserDto;
import org.example.common.dto.user.VerifyUserDto;
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
@AllArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;
    private final Converter converter;
    private final UserFacade userFacade;

    @GetMapping("/me")
    protected AppUserDto getUser(@RequestBody final String login) {
        final User user = userService.getUser(login).stream().findFirst().orElse(null);
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

    @GetMapping("/all")
    protected List<AppUserDto> getAllUsers() {
        List<User> listUsers = userService.findUsers();
        return converter.toDto(listUsers);
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
