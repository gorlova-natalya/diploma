package com.teachmeskills.documents.converter;

import com.teachmeskills.documents.dto.DocumentTypeDto;
import com.teachmeskills.documents.model.DocumentType;
import org.mapstruct.Mapper;

@Mapper
public interface DocumentTypeConverter {

    DocumentTypeDto toDto(DocumentType documentType);

    DocumentType toEntity(DocumentTypeDto documentTypeDto);
}
