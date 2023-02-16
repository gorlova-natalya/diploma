package com.teachmeskills.users;

import com.teachmeskills.users.controller.UserController;
import com.teachmeskills.users.converter.Converter;
import com.teachmeskills.users.facade.UserFacade;
import com.teachmeskills.users.model.Role;
import com.teachmeskills.users.model.User;
import com.teachmeskills.users.repository.UserRepository;
import com.teachmeskills.users.service.UserService;
import org.example.common.dto.user.CreateUserDto;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.verification.VerificationMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Disabled
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = UserController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class)
@AutoConfigureMockMvc(addFilters = false)
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper mapper = new ObjectMapper();

    @MockBean
    private UserFacade userFacade;

    @MockBean
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private Converter converter;

    @Test
    public void shouldCreateUser() throws Exception {
        final String login = "Natasha";
        final String password = "1231";
        final String role = "user";
        final CreateUserDto dto = CreateUserDto.builder().login(login).password(password).role(role).build();

        mockMvc.perform(post("/api/v1/users", dto)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(APPLICATION_JSON))
                .andExpect(status().isAccepted()).andReturn();

        then(userFacade)
                .should()
                .createUser(login, password, role);
    }

    @Test
    public void shouldCreateUserWithEmptyPassword() throws Exception {
        final String login = "Natasha8";
        final String password = null;
        final String role = "user";
        final CreateUserDto dto = CreateUserDto.builder().login(login).password(password).role(role).build();

        mockMvc.perform(post("/api/v1/users", dto)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(APPLICATION_JSON))
                .andExpect(status().is4xxClientError());

        then(userFacade)
                .shouldHaveNoInteractions();
    }

    @Test
    public void createUserTest() throws Exception {
        Role role = new Role();
        role.setId(1);
        role.setName("admin");
        User user = new User("Name", "password", role);

        mockMvc.perform(post("/users", user)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()).andReturn();

        when(userService.createUser("Name", "password", role)).thenReturn(user);
        then(userService.createUser("Name", "password", role))
                .should((VerificationMode) willReturn(user));
    }

    @Test
    public void testCreateUserSuccess() throws Exception {
        Role role = new Role();
        role.setId(1);
        role.setName("admin");
        User user = new User("Name", "password", role);

        given(userService.createUser("Name", "password", role)).willReturn(user);

        String res = mockMvc.perform(post("/api/v1/users")
                        .content(mapper.writeValueAsString(user))
                        .contentType(APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        User responseUser = mapper.readValue(res, User.class);
        assertEquals(user, responseUser);
    }

    @Test
    public void whenSaveUser() {
        Role role = new Role();
        role.setId(1);
        role.setName("admin");
        User user = new User("Евгений", "123", role);
        when(userRepository.save(user)).thenReturn(user);
        User created = userService.createUser("Евгений", "123", role);
        assertThat(created.getLogin()).isSameAs(user.getLogin());
        verify(userRepository).save(user);
    }

    @Test
    public void shouldReturnAllUsers() {
        List<User> users = new ArrayList();
        users.add(new User());
        given(userRepository.findAll()).willReturn(users);
        List<User> expected = userService.findUsers();
        assertEquals(expected, users);
        verify(userRepository).findAll();
    }
}
