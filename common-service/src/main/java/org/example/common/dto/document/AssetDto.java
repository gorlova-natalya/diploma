package org.example.common.dto.document;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Jacksonized
@RequiredArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
public class AssetDto {

    Long id;
    String assetName;
    Integer assetCount;
    Integer assetNumber;
    Double cost;
    DepartmentDto department;
    EmployeeDto employee;
    AssetUnitDto assetUnits;
}
