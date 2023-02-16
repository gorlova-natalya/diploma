package com.teachmeskills.security.config.service;

import com.teachmeskills.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.example.common.dto.user.AppUserDto;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(final String login) throws UsernameNotFoundException {
        final AppUserDto user = userService.getUser(login);
        return new User(user.getLogin(), user.getPassword(), List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole())));
    }
}
