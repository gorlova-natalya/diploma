package com.teachmeskills.security.service;

import com.teachmeskills.security.client.DocumentClient;
import com.teachmeskills.security.dto.DocumentTypeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentService {

    private final DocumentClient documentClient;

    public DocumentTypeDto getDocumentTypeById(final Long id) {
        return documentClient.getDocumentTypeById(id);
    }

    public List<DocumentTypeDto> getDocumentTypes() {
        return documentClient.getDocumentTypes();
    }
}
