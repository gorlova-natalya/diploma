package com.teachmeskills.documents.converter;

import com.teachmeskills.documents.model.AssetUnit;
import org.example.common.dto.document.AssetUnitDto;
import org.mapstruct.Mapper;

@Mapper
public interface AssetUnitConverter {

    AssetUnitDto toDto(AssetUnit assetUnit);
}
