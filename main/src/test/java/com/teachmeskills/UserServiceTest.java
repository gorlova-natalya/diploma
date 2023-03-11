package com.teachmeskills;

import com.teachmeskills.security.client.UserClient;
import com.teachmeskills.security.service.UserService;
import org.example.common.dto.user.AppUserDto;
import org.example.common.dto.user.CreateUserDto;
import org.example.common.dto.user.PageDto;
import org.example.common.dto.user.UsersListDto;
import org.example.common.dto.user.VerifyResultDto;
import org.example.common.dto.user.VerifyUserDto;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@Disabled
@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = UserService.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserClient userClient;

    @Test
    public void getAllUsersTest() {
        List<AppUserDto> users = new ArrayList<>();

        given(userClient.getAllUsers()).willReturn(users);
        List<AppUserDto> expected = userService.getAllUsers();

        assertEquals(expected, users);

        verify(userClient).getAllUsers();
    }

    @Test
    public void getUserByLoginTest() {
        String login = "Nick";
        AppUserDto user = new AppUserDto(1L, "Nick", "123", "user");

        given(userClient.getUser(login)).willReturn(user);
        AppUserDto expected = userService.getUser(login);

        assertEquals(expected, user);

        verify(userClient).getUser(login);
    }

    @Test
    public void createUserTest() {

        CreateUserDto user = new CreateUserDto("Nick", "123", "user");
        AppUserDto created = new AppUserDto(1L, "Nick", "123", "user");

        given(userClient.createUser(user)).willReturn(created);
        AppUserDto expected = userService.createUser(user);

        assertEquals(expected, created);

        verify(userClient).createUser(user);
    }

    @Test
    public void verifyUserTest() {
        String login = "Nick";
        String password = "123";
        VerifyUserDto user = new VerifyUserDto(login, password);
        VerifyResultDto result = new VerifyResultDto(true);

        given(userClient.verifyUser(user)).willReturn(result);
        VerifyResultDto expected = userService.verifyUser(login, password);

        assertEquals(expected, result);

        verify(userClient).verifyUser(user);
    }

    @Test
    public void getPageOfUsersTest() {
        List<AppUserDto> listUserDto = List.of(
                new AppUserDto(0, "Евгений", "123", "admin"),
                new AppUserDto(0, "Анастасия", "123", "user"),
                new AppUserDto(0, "Дмитрий", "123", "user")
        );
        UsersListDto expected = UsersListDto.builder().listUsers(listUserDto).totalPages(2)
                .totalElements(8).build();
        PageDto dto = new PageDto(1, 3);
        given(userClient.getUsers(dto)).willReturn(expected);

        UsersListDto returned = userService.getUsers(dto);

        assertEquals(expected, returned);

        verify(userClient).getUsers(dto);
    }
}
