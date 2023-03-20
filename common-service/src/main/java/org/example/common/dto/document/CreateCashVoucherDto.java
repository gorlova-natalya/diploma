package org.example.common.dto.document;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Value
@Jacksonized
@Builder
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class CreateCashVoucherDto {

    String purpose;
    @NotNull(message = "Employee must not be empty")
    Long employeeId;
    Long documentTypeId;
    Long organizationId;
    @NotNull(message = "Sum must not be empty")
    double sum;
    @NotNull(message = "Document number must not be empty")
    int documentNumber;
    @NotEmpty(message = "Document date must not be empty")
    String documentDate;
    String annex;
    String passport;
}
