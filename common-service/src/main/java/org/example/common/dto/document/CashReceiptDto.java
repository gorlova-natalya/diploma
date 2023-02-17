package org.example.common.dto.document;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.jackson.Jacksonized;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@Jacksonized
@RequiredArgsConstructor
public class CashReceiptDto {

    String purpose;
    EmployeeDto employee;
    DocumentTypeDto documentType;
    OrganizationDto organization;
    double sum;
    int documentNumber;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "dd.MM.yyyy")
    LocalDate documentDate;
    String annex;
}
