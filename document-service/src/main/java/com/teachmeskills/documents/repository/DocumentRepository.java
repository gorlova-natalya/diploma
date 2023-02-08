package com.teachmeskills.documents.repository;

import com.teachmeskills.documents.model.DocumentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<DocumentType, Long> {

    DocumentType getDocumentTypeById(Long id);
}
