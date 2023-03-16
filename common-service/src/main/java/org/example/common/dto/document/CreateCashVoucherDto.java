package org.example.common.dto.document;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Jacksonized
@Builder
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class CreateCashVoucherDto {

    String purpose;
    Long employeeId;
    Long documentTypeId;
    Long organizationId;
    double sum;
    int documentNumber;
    String documentDate;
    String annex;
    String passport;
}
