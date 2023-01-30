package com.teachmeskills.users.converter;

import com.teachmeskills.users.dto.AppUserDto;
import com.teachmeskills.users.model.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface UserConverter {

    List<AppUserDto> toDto(List<User> users);

    AppUserDto toDto(User user);
}
