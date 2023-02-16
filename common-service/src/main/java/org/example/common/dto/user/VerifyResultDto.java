package org.example.common.dto.user;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Value
@Builder
public class VerifyResultDto {

    boolean valid;
}
