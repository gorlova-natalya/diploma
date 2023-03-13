package com.teachmeskills.users;

import com.teachmeskills.users.controller.UserController;
import com.teachmeskills.users.converter.Converter;
import com.teachmeskills.users.facade.UserFacade;
import com.teachmeskills.users.model.Role;
import com.teachmeskills.users.model.User;
import com.teachmeskills.users.repository.UserRepository;
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
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.testcontainers.shaded.com.fasterxml.jackson.core.type.TypeReference;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Disabled
@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
@AutoConfigureMockMvc(addFilters = false)
@ContextConfiguration(classes = UserController.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper mapper = new ObjectMapper();

    @MockBean
    private UserFacade userFacade;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private Converter converter;

    @Test
    public void createUserSuccessTest() throws Exception {
        Role role = new Role(1L, "admin");
        User user = new User("Name", "password", role);
        user.setId(5L);
        final CreateUserDto createUserDto = new CreateUserDto("Name", "password", "admin");

        when(userFacade.createUser(createUserDto.getLogin(), createUserDto.getPassword(), createUserDto.getRole()))
                .thenReturn(user);

        final AppUserDto expectedUser = AppUserDto.builder().id(user.getId()).login(user.getLogin())
                .password(user.getPassword()).role(role.getName()).build();

        when(converter.toDto(user)).thenReturn(expectedUser);

        MvcResult mvcResult = mockMvc.perform(post("/api/v1/users")
                        .content(mapper.writeValueAsString(createUserDto))
                        .contentType(APPLICATION_JSON_VALUE)
                        .characterEncoding("utf-8")
                )
                .andDo(print())
                .andExpect(status().isAccepted())
                .andReturn();

        then(userFacade)
                .should()
                .createUser(createUserDto.getLogin(), createUserDto.getPassword(), createUserDto.getRole());

        AppUserDto returnedUser = mapper.readValue(mvcResult.getResponse().getContentAsString(),
                new TypeReference<AppUserDto>() {
                });
        assertEquals(expectedUser, returnedUser);
    }

    @Test
    public void errorWhenCreateUserWithEmptyPasswordTest() throws Exception {
        final String login = "Natasha8";
        final String password = null;
        final String role = "user";
        final CreateUserDto dto = CreateUserDto.builder().login(login).password(password).role(role).build();

        mockMvc.perform(post("/api/v1/users")
                        .content(String.valueOf(dto))
                        .contentType(APPLICATION_JSON_VALUE)
                        .accept(APPLICATION_JSON))
                .andExpect(status().is4xxClientError());

        then(userFacade)
                .shouldHaveNoInteractions();
    }

    @Test
    public void getAllUsersTest() throws Exception {
        User user = new User("Natasha", "123", new Role(1L, "admin"));
        user.setId(1L);
        List<User> allUsers = List.of(user);
        List<AppUserDto> expectedUsers = allUsers.stream().map(usr -> AppUserDto.builder().id(usr.getId())
                        .login(usr.getLogin()).password(usr.getPassword()).role(usr.getRole().getName()).build())
                .collect(Collectors.toList());

        when(userFacade.findUsers()).thenReturn(allUsers);
        when(converter.toDto(allUsers)).thenReturn(expectedUsers);

        MvcResult mvcResult = mockMvc.perform(get("/api/v1/users/all")
                        .content(String.valueOf(user))
                        .contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        then(userFacade)
                .should()
                .findUsers();

        List<AppUserDto> returnedUsers = mapper.readValue(mvcResult.getResponse().getContentAsString(),
                new TypeReference<List<AppUserDto>>() {
                });
        assertEquals(expectedUsers, returnedUsers);
    }

    @Test
    public void getUserByLoginTest() throws Exception {

        User user = new User("Natasha", "123", new Role(1L, "admin"));
        user.setId(1L);
        AppUserDto expectedUser = AppUserDto.builder().id(user.getId())
                .login(user.getLogin()).password(user.getPassword()).role(user.getRole().getName()).build();
        when(userFacade.getUser(user.getLogin())).thenReturn(user);
        when(converter.toDto(user)).thenReturn(expectedUser);

        MvcResult mvcResult = mockMvc.perform(get("/api/v1/users/me")
                        .content(String.valueOf(user.getLogin()))
                        .contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        then(userFacade)
                .should()
                .getUser(user.getLogin());

        AppUserDto returnedUser = mapper.readValue(mvcResult.getResponse().getContentAsString(),
                new TypeReference<AppUserDto>() {
                });
        assertEquals(expectedUser, returnedUser);
    }

    @Test
    public void verifyUserTest() throws Exception {

        VerifyUserDto verifyUserDto = new VerifyUserDto("Natasha2", "123");
        boolean expected = true;

        when(userFacade.verify(verifyUserDto.getLogin(), verifyUserDto.getPassword()))
                .thenReturn(expected);
        VerifyResultDto expectedResult = VerifyResultDto.builder().valid(expected).build();

        MvcResult mvcResult = mockMvc.perform(post("/api/v1/users/verify")
                        .content(mapper.writeValueAsString(verifyUserDto))
                        .contentType(APPLICATION_JSON)
                        .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        then(userFacade)
                .should()
                .verify(verifyUserDto.getLogin(), verifyUserDto.getPassword());

        final VerifyResultDto returned = mapper.readValue(mvcResult.getResponse().getContentAsString(),
                new TypeReference<VerifyResultDto>() {
                });

        assertEquals(expectedResult, returned);
    }

    @Test
    public void getPaginatedUsersTest() throws Exception {
        List<User> users = new ArrayList<>();
        users.add(new User("Евгений", "123", new Role(1L, "admin")));
        users.add(new User("Анастасия", "123", new Role(2L, "user")));
        users.add(new User("Дмитрий", "123", new Role(2L, "user")));
        users.add(new User("Александр", "123", new Role(2L, "user")));
        users.add(new User("Фёдор", "123", new Role(2L, "user")));
        users.add(new User("Наталья", "123", new Role(1L, "admin")));
        users.add(new User("Ольга", "123", new Role(2L, "user")));
        users.add(new User("Артем", "123", new Role(1L, "admin")));
        List<User> usersWithId = users.stream().peek(user -> user.setId(0)).collect(Collectors.toList());

        PageDto dto = new PageDto(1, 3);
        Page<User> page = mock(Page.class);
        List<User> pageOfUsers = usersWithId.subList(0, 3);
        int totalPages = 3;
        long totalElements = 8L;

        when(page.getContent()).thenReturn(pageOfUsers);
        when(page.getTotalPages()).thenReturn(totalPages);
        when(page.getTotalElements()).thenReturn(totalElements);

        when(userRepository.findAll(PageRequest.of(dto.getPageNo(), dto.getPageSize()))).thenReturn(page);

        when(userFacade.findPaginatedUsers(dto.getPageNo(), dto.getPageSize())).thenReturn(page);
        List<AppUserDto> listUserDto = List.of(
                new AppUserDto(0, "Евгений", "123", "admin"),
                new AppUserDto(0, "Анастасия", "123", "user"),
                new AppUserDto(0, "Дмитрий", "123", "user")
        );

        when(converter.toDto(pageOfUsers)).thenReturn(listUserDto);

        UsersListDto expected = UsersListDto.builder().listUsers(listUserDto).totalPages(totalPages)
                .totalElements(totalElements).build();

        MvcResult mvcResult = mockMvc.perform(get("/api/v1/users")
                        .content(mapper.writeValueAsString(dto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        final UsersListDto returned = mapper.readValue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8),
                new TypeReference<UsersListDto>() {
                });

        assertEquals(expected, returned);
    }
}
