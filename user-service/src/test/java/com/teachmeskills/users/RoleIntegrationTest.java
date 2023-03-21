package com.teachmeskills.users;

import com.teachmeskills.users.model.Role;
import com.teachmeskills.users.repository.RoleRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RoleIntegrationTest extends AbstractIntegrationUsersTest {

    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void getRoleByRoleNameTest() {
        Role expected = new Role(2L, "user");
        Role result = roleRepository.getRoleByName("user");

        assertEquals(result, expected);
    }
}
