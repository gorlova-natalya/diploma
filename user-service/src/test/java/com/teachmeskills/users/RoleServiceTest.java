package com.teachmeskills.users;

import com.teachmeskills.users.model.Role;
import com.teachmeskills.users.repository.RoleRepository;
import com.teachmeskills.users.service.RoleService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@Disabled
@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = RoleService.class)
public class RoleServiceTest {

    @Autowired
    private RoleService roleService;

    @MockBean
    private RoleRepository roleRepository;

    @Test
    public void shouldReturnRoleByName() {
        Role role = new Role(1L, "admin");

        given(roleRepository.getRoleByName(role.getName())).willReturn(role);
        Role expected = roleService.getRoleByName("admin");

        assertEquals(expected, role);

        verify(roleRepository).getRoleByName(role.getName());
    }
}
