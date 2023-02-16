package com.teachmeskills.documents.converter;

import com.teachmeskills.documents.model.Asset;
import org.example.common.dto.document.AssetDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(uses = {DepartmentConverter.class, EmployeeConverter.class, AssetUnitConverter.class})
public interface AssetConverter {

    AssetDto toDto(Asset asset);

    List<AssetDto> toDto(List<Asset> asset);
}
