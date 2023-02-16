package com.teachmeskills.documents.facade;

import org.example.common.dto.document.CreateCashReceiptDto;
import org.example.common.dto.document.CreateCashVoucherDto;
import com.teachmeskills.documents.model.CashReceipt;
import com.teachmeskills.documents.model.CashVoucher;
import com.teachmeskills.documents.model.DocumentType;
import com.teachmeskills.documents.model.Employee;
import com.teachmeskills.documents.model.Organization;
import com.teachmeskills.documents.service.DocumentService;
import com.teachmeskills.documents.service.EmployeeService;
import com.teachmeskills.documents.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentFacade {

    private final DocumentService documentService;
    private final EmployeeService employeeService;
    private final OrganizationService organizationService;

    public CashReceipt createCashReceipt(CreateCashReceiptDto dto) {
        Organization organization = organizationService
                .get(dto.getOrganizationId()).stream().findFirst().orElse(null);
        Employee employee = employeeService.get(dto.getEmployeeId()).stream().findFirst().orElse(null);
        DocumentType documentType = documentService.getDocumentType(dto.getDocumentTypeId()).stream().findFirst().orElse(null);

        return documentService.createCashReceipt(documentType,
                dto.getDocumentNumber(), dto.getPurpose(), dto.getDocumentDate(),
               employee, organization, dto.getSum(), dto.getAnnex());
    }

    public CashVoucher createCashVoucher(CreateCashVoucherDto dto) {
        Organization organization = organizationService
                .get(dto.getOrganizationId()).stream().findFirst().orElse(null);
        Employee employee = employeeService.get(dto.getEmployeeId()).stream().findFirst().orElse(null);
        DocumentType documentType = documentService.getDocumentType(dto.getDocumentTypeId()).stream().findFirst().orElse(null);

        return documentService.createCashVoucher(documentType,
                dto.getDocumentNumber(), dto.getPurpose(), dto.getDocumentDate(),
                employee, organization, dto.getSum(), dto.getAnnex(), dto.getPassport());
    }

    public DocumentType getDocumentType(Long id) {
        DocumentType documentType1 = documentService.getDocumentType(id).stream().findFirst().orElse(null);
        List<Employee> employees = employeeService.getEmployeesBySignedDocumentsContains(documentType1);
        DocumentType documentType = new DocumentType();
        documentType.setId(id);
        assert documentType1 != null;
        documentType.setTypeName(documentType1.getTypeName());
        documentType.setSignersList(employees);
        return documentType;
    }

    public List<DocumentType> getDocumentTypes() {
        return documentService.getDocumentTypes();
    }
}
