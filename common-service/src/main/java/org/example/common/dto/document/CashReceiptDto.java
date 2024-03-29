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
public class CashReceiptDto {

    String purpose;
    EmployeeDto employee;
    DocumentTypeDto documentType;
    OrganizationDto organization;
    double sum;
    int documentNumber;
    String documentDate;
    String annex;
}
