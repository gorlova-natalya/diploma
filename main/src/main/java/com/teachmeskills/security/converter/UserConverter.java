package com.teachmeskills.security.converter;

import com.teachmeskills.security.client.dto.CreateUserDto;
import com.teachmeskills.security.dto.UserDtoRest;
import org.mapstruct.Mapper;
import org.springframework.security.core.userdetails.User;

import java.util.List;

@Mapper
public interface UserConverter {

  List<UserDtoRest> toDto(List<User> users);

  CreateUserDto toDto(User user);
}
