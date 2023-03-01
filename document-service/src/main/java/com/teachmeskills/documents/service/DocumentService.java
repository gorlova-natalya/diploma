package com.teachmeskills.documents.service;

import com.teachmeskills.documents.model.DocumentType;
import com.teachmeskills.documents.repository.DocumentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class DocumentService {

    private final DocumentRepository documentRepository;

    public Optional<DocumentType> getDocumentType(Long id) {
        return documentRepository.getDocumentTypeById(id);
    }

    public List<DocumentType> getDocumentTypes() {
        return documentRepository.findAll();
    }
}
