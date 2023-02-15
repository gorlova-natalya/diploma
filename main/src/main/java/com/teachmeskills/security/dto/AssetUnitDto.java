package com.teachmeskills.security.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.jackson.Jacksonized;

@Data
@Jacksonized
@RequiredArgsConstructor
public class AssetUnitDto {

    Long id;
    String unitName;
}
