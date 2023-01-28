package com.teachmeskills.security.client.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PageDto {

    int pageNo;
    int pageSize;
}
