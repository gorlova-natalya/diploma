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
public class InvoiceDto {

    Long id;
    int documentNumber;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "dd.MM.yyyy")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    LocalDate documentDate;
    OrganizationDto organization;
    DepartmentDto fromDepartment;
    DepartmentDto toDepartment;
    EmployeeDto fromEmployee;
    EmployeeDto toEmployee;
    DocumentTypeDto documentType;
    AssetDto asset;
}
