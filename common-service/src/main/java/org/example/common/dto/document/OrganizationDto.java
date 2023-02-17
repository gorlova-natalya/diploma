package org.example.common.dto.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

@Data
@Jacksonized
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationDto {

    Long id;
    String name;
    int payerNumber;
    EmployeeDto supervisor;
}