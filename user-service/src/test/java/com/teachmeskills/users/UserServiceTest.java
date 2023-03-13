package com.teachmeskills.users;

import com.teachmeskills.users.model.Role;
import com.teachmeskills.users.model.User;
import com.teachmeskills.users.repository.UserRepository;
import com.teachmeskills.users.service.HashPassword;
import com.teachmeskills.users.service.UserService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Disabled
@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = UserService.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private HashPassword hashPassword;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void createUserTest() {
        Role role = new Role();
        role.setId(1);
        role.setName("admin");
        final User user = new User("Евгений", hashPassword.hashingPassword("123"), role);

        when(userRepository.save(user)).thenReturn(user);
        User created = userService.createUser("Евгений", "123", role);

        assertThat(created.getLogin()).isSameAs(user.getLogin());

        verify(userRepository).save(user);
    }

    @Test
    public void getAllUsersTest() {
        List<User> users = new ArrayList<>();
        users.add(new User("Евгений", "123", new Role(1L, "admin")));

        given(userRepository.findAll()).willReturn(users);
        List<User> expected = userService.findUsers();

        assertEquals(expected, users);

        verify(userRepository).findAll();
    }

    @Test
    public void getPageOfUsersTest() {
        List<User> users = new ArrayList<>();
        users.add(new User("Евгений", "123", new Role(1L, "admin")));
        users.add(new User("Анастасия", "123", new Role(1L, "user")));
        users.add(new User("Дмитрий", "123", new Role(1L, "user")));
        users.add(new User("Александр", "123", new Role(1L, "user")));
        users.add(new User("Фёдор", "123", new Role(1L, "user")));
        users.add(new User("Наталья", "123", new Role(1L, "admin")));
        users.add(new User("Ольга", "123", new Role(1L, "user")));
        users.add(new User("Артем", "123", new Role(1L, "admin")));
        Page<User> paginated = userRepository.findAll(PageRequest.of(2, 3));
        Page<User> paginatedUsers = userService.findPaginatedUsers(2, 3);

        given(userRepository.findAll(PageRequest.of(2, 3))).willReturn(paginated);

        assertEquals(paginatedUsers, paginated);

        verify(userRepository).findAll(PageRequest.of(2, 3));
    }

    @Test
    public void verifyUserTest() {
        String encodedPassword = "encodedPassword";
        Role role = new Role();
        role.setId(1);
        role.setName("admin");
        final User user = new User("Евгений", hashPassword.hashingPassword("123"), role);

        given(userRepository.getUserByLogin(user.getLogin())).willReturn(Optional.of(user));
        given(hashPassword.validatePassword(encodedPassword, user.getPassword())).willReturn(true);
        boolean verify = userService.verify(user.getLogin(), encodedPassword);

        assertTrue(verify);
    }

    @Test
    public void getUserByLoginTest() {
        Role role = new Role();
        role.setId(1);
        role.setName("admin");
        final User user = new User("Евгений", hashPassword.hashingPassword("123"), role);
        final Optional<User> expectedUser = Optional.of(user);

        given(userRepository.getUserByLogin(user.getLogin())).willReturn(expectedUser);
        Optional<User> returnedUser = userService.getUser(user.getLogin());

        assertEquals(returnedUser, expectedUser);
    }
}
