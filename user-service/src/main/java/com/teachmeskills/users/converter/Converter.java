package com.teachmeskills.users.converter;

import com.teachmeskills.users.model.User;
import lombok.RequiredArgsConstructor;
import org.example.common.dto.user.AppUserDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class Converter {

    public List<AppUserDto> toDto(List<User> users) {
        if (users == null) {
            return null;
        }
        List<AppUserDto> list = new ArrayList<AppUserDto>(users.size());
        for (User user : users) {
            list.add(AppUserDto.builder().id(user.getId()).login(user.getLogin()).password(user.getPassword())
                            .role(user.getRole().getName()).build());
        }
        return list;
    }
}
