package org.example.common.dto.user;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Jacksonized
@Value
@Builder
public class UsersListDto {

    List<AppUserDto> listUsers;
    int totalPages;
    long totalElements;
}
