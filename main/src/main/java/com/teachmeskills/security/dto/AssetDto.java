package com.teachmeskills.security.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.jackson.Jacksonized;

@Data
@Jacksonized
@RequiredArgsConstructor
public class AssetDto {

    Long id;
    String assetName;
    Integer assetCount;
    Double cost;
    DepartmentDto department;
    EmployeeDto employee;
    AssetUnitDto assetUnits;
}
