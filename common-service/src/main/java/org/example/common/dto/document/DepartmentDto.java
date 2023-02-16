package org.example.common.dto.document;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.jackson.Jacksonized;

@Data
@Jacksonized
@RequiredArgsConstructor
public class DepartmentDto {

    Long id;
    String departmentName;
    OrganizationDto organization;
}
