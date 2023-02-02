package com.teachmeskills.users;

import com.teachmeskills.users.controller.UserController;
import com.teachmeskills.users.dto.Converter;
import com.teachmeskills.users.dto.CreateUserDto;
import com.teachmeskills.users.facade.UserFacade;
import com.teachmeskills.users.service.UserService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Disabled
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = UserController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class)
@AutoConfigureMockMvc(addFilters = false)
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserFacade userFacade;

    @MockBean
    private UserService userService;

    @MockBean
    private Converter converter;

    @Test
    public void shouldCreateUser() throws Exception {
        final String login = "Natasha";
        final String password = "1231";
        final String role = "user";
        final CreateUserDto dto = CreateUserDto.builder().login(login).password(password).role(role).build();

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/users", dto)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON))
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

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/users", dto)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());

        then(userFacade)
                .shouldHaveNoInteractions();
    }
}
