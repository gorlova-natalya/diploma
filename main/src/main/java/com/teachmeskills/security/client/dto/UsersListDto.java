package com.teachmeskills.security.client.dto;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Value
@Builder
@Jacksonized
public class UsersListDto {

    List<AppUserDto> listUsers;
    int totalPages;
    long totalElements;
}
