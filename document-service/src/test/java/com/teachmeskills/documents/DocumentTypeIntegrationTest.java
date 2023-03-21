package com.teachmeskills.documents;

import com.teachmeskills.documents.model.DocumentType;
import com.teachmeskills.documents.repository.DocumentTypeRepository;
import org.junit.Test;
import org.junit.jupiter.api.Disabled;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Disabled
public class DocumentTypeIntegrationTest extends AbstractIntegrationDocumentsTest {

    @Autowired
    private DocumentTypeRepository documentTypeRepository;

    @Test
    public void getDocumentTypeByIdTest() {
        Long id = 1L;
        String typeName = "Приходный кассовый ордер";
        Optional<DocumentType> result = documentTypeRepository.findById(id);

        assertEquals(result.get().getTypeName(), typeName);
    }
}
