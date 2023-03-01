package com.teachmeskills.documents.converter;

import com.teachmeskills.documents.model.AssetCount;
import org.example.common.dto.document.AssetCountDto;
import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;

import java.util.List;

@Mapper(uses = {AssetConverter.class})
public interface AssetCountConverter {

//    @Mapping(target = "id", source = "id")
//    @Mapping(target = "asset", source = "asset")
//    @Mapping(target = "count", source = "count")
//    @Mapping(target = "sum", source = "sum")
    AssetCountDto toDto(AssetCount assetCount);

    List<AssetCountDto> toDto(List<AssetCount> assetCounts);
}
