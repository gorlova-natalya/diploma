package org.example.common.dto.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.jackson.Jacksonized;

@Data
@Jacksonized
@RequiredArgsConstructor
@AllArgsConstructor
public class ChooseAssetDto {

    Long assetId;
    int count;
}
