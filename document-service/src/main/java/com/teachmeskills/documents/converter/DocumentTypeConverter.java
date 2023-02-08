package com.teachmeskills.documents.converter;

import com.teachmeskills.documents.dto.DocumentTypeDto;
import com.teachmeskills.documents.model.DocumentType;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(uses = EmployeeConverter.class)
public interface DocumentTypeConverter {

    DocumentTypeDto toDto(DocumentType documentType);

    DocumentType toEntity(DocumentTypeDto documentTypeDto);

    List<DocumentTypeDto> toDto(List<DocumentType> documentType);
}
