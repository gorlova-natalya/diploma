package com.teachmeskills.documents;

import com.teachmeskills.documents.model.DocumentType;
import com.teachmeskills.documents.model.Employee;
import com.teachmeskills.documents.repository.DocumentTypeRepository;
import com.teachmeskills.documents.service.DocumentTypeService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@Disabled
@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = DocumentTypeService.class)
public class DocumentTypeServiceTest {

    @Autowired
    private DocumentTypeService documentTypeService;

    @MockBean
    private DocumentTypeRepository documentTypeRepository;

    @Test
    public void getAllDocumentTypesTest() {
        DocumentType documentType1 = new DocumentType();
        documentType1.setId(1L);
        documentType1.setTypeName("Накладная");
        documentType1.setSignersList(new ArrayList<>());

        DocumentType documentType2 = new DocumentType();
        documentType2.setId(2L);
        documentType2.setTypeName("Акт");
        documentType2.setSignersList(new ArrayList<>());

        List<DocumentType> allTypes = List.of(documentType1, documentType2);

        given(documentTypeRepository.findAll()).willReturn(allTypes);
        List<DocumentType> expected = documentTypeService.getDocumentTypes();

        assertEquals(expected, allTypes);

        verify(documentTypeRepository).findAll();
    }

    @Test
    public void getDocumentTypeByIdTest() {
        DocumentType documentType = new DocumentType();
        documentType.setId(1L);
        documentType.setTypeName("Накладная");
        documentType.setSignersList(new ArrayList<>(List.of(new Employee())));

        final Optional<DocumentType> expected = Optional.of(documentType);

        given(documentTypeRepository.getDocumentTypeById(documentType.getId())).willReturn(expected);

        Optional<DocumentType> returned = documentTypeService.getDocumentType(documentType.getId());

        assertEquals(expected, returned);

        verify(documentTypeRepository).getDocumentTypeById(documentType.getId());
    }
}
