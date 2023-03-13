package com.teachmeskills.documents.converter;

import org.example.common.dto.document.PositionDto;
import com.teachmeskills.documents.model.Position;
import org.mapstruct.Mapper;

@Mapper
public interface PositionConverter {

    PositionDto toDto(Position position);
}
