package com.teachmeskills.documents.converter;

import com.teachmeskills.documents.model.DocumentType;
import org.example.common.dto.document.DocumentTypeDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(uses = EmployeeConverter.class)
public interface DocumentTypeConverter {

    DocumentTypeDto toDto(DocumentType documentType);

    List<DocumentTypeDto> toDto(List<DocumentType> documentType);

    DocumentType toEntity(DocumentTypeDto dto);
}
