package com.teachmeskills.users.dto;

import com.teachmeskills.users.model.User;
import lombok.RequiredArgsConstructor;
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
            list.add(new AppUserDto(user.getId(), user.getLogin(), user.getPassword(), user.getRole().getName()));
        }

        return list;
    }
}
