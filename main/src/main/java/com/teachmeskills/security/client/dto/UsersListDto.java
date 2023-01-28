package com.teachmeskills.security.client.dto;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class UsersListDto {

    List<AppUserDto> listUsers;
    int totalPages;
    int totalElements;
}
