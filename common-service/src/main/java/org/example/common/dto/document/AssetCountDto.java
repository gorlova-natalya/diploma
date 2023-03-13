package org.example.common.dto.document;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.jackson.Jacksonized;

@Data
@Jacksonized
@RequiredArgsConstructor
public class AssetCountDto {

    Long id;
    AssetDto asset;
    int count;
    Double sum;
}
