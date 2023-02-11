package com.teachmeskills.security.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.jackson.Jacksonized;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@Jacksonized
@RequiredArgsConstructor
public class CashVoucherDto {

    String purpose;
    EmployeeDto employee;
    DocumentTypeDto documentType;
    OrganizationDto organization;
    double sum;
    int documentNumber;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd")
    LocalDate documentDate;
    String annex;
    String passport;
}
