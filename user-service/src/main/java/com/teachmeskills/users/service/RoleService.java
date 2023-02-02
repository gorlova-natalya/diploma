package com.teachmeskills.users.service;

import com.teachmeskills.users.model.Role;
import com.teachmeskills.users.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public Role getRoleByName(String roleName) {
        return roleRepository.getRoleByName(roleName);
    }
}
