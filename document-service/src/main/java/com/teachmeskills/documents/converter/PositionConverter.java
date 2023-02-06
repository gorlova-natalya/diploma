package com.teachmeskills.documents.converter;

import com.teachmeskills.documents.dto.PositionDto;
import com.teachmeskills.documents.model.Position;
import org.mapstruct.Mapper;

@Mapper
public interface PositionConverter {

    PositionDto toDto(Position position);
}
