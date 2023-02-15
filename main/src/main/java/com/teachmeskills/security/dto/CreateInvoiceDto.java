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
public class CreateInvoiceDto {

    Long id;
    int documentNumber;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "dd.MM.yyyy")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    LocalDate documentDate;
    Long organization;
    Long fromDepartment;
    Long toDepartment;
    Long fromEmployee;
    Long toEmployee;
    Long documentType;
    Long asset;
}
