package com.teachmeskills.documents.facade;

import com.teachmeskills.documents.model.DocumentType;
import com.teachmeskills.documents.service.DocumentTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentTypeFacade {

    private final DocumentTypeService documentTypeService;

    public DocumentType getDocumentType(Long id) {
        return documentTypeService.getDocumentType(id).orElse(null);
    }

    public List<DocumentType> getDocumentTypes() {
        return documentTypeService.getDocumentTypes();
    }
}
