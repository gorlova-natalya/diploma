package com.teachmeskills.documents.facade;

import com.teachmeskills.documents.converter.DocumentTypeConverter;
import com.teachmeskills.documents.converter.EmployeeConverter;
import com.teachmeskills.documents.converter.OrganizationConverter;
import com.teachmeskills.documents.dto.DocumentTypeDto;
import com.teachmeskills.documents.dto.EmployeeDto;
import com.teachmeskills.documents.dto.OrganizationDto;
import com.teachmeskills.documents.model.CashReceipt;
import com.teachmeskills.documents.model.DocumentType;
import com.teachmeskills.documents.service.DocumentService;
import com.teachmeskills.documents.service.EmployeeService;
import com.teachmeskills.documents.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentFacade {

    private final DocumentService documentService;
    private final EmployeeService employeeService;
    private final OrganizationService organizationService;
    private final OrganizationConverter organizationConverter;
    private final EmployeeConverter employeeConverter;
    private final DocumentTypeConverter documentTypeConverter;

    public CashReceipt createCashReceipt(DocumentTypeDto documentTypeDto, int documentNumber, String purpose,
                                         LocalDate date, EmployeeDto employee, OrganizationDto organization, double sum,
                                         String annex) {
        return documentService.createCashReceipt(documentTypeConverter.toEntity(documentTypeDto), documentNumber, purpose, date, employeeConverter.toEntity(employee),
               organizationService.getOrganizationByName(organization.getName()), sum, annex);
    }

    public DocumentType getDocumentType(long id){
        return documentService.getDocumentType(id);
    }

    public List<DocumentType> getDocumentTypes() {
        return documentService.getDocumentTypes();
    }
}
