package com.teachmeskills.documents.repository;

import com.teachmeskills.documents.model.DocumentType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<DocumentType, Long> {

    DocumentType getDocumentTypeById(long id);
}
