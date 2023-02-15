package com.teachmeskills.documents.dto;

import com.teachmeskills.documents.model.Department;
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
    Department department;
    EmployeeDto employee;
    AssetUnitDto assetUnits;
}
