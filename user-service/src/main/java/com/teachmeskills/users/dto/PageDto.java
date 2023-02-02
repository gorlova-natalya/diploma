package com.teachmeskills.users.dto;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class PageDto {

    int pageNo;
    int pageSize;
}
