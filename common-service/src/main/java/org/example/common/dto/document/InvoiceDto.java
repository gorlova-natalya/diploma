package org.example.common.dto.document;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Value
@Jacksonized
@Builder
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class InvoiceDto {

    Long id;
    int documentNumber;
    String documentDate;
    OrganizationDto organization;
    DepartmentDto fromDepartment;
    DepartmentDto toDepartment;
    EmployeeDto fromEmployee;
    EmployeeDto toEmployee;
    DocumentTypeDto documentType;
    List<AssetCountDto> assetCount;
}
