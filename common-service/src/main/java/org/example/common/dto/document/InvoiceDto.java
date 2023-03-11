package org.example.common.dto.document;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Value
@Jacksonized
@Builder
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class InvoiceDto {

    Long id;
    int documentNumber;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd")
    LocalDate documentDate;
    OrganizationDto organization;
    DepartmentDto fromDepartment;
    DepartmentDto toDepartment;
    EmployeeDto fromEmployee;
    EmployeeDto toEmployee;
    DocumentTypeDto documentType;
    List<AssetCountDto> assetCount;
}
