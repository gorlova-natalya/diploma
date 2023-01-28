package com.teachmeskills.users.dto;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Value
@Builder
public class VerifyResultDto {

    boolean valid;
}
