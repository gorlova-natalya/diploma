package com.teachmeskills.documents.facade;

import com.teachmeskills.documents.model.Asset;
import com.teachmeskills.documents.model.Department;
import com.teachmeskills.documents.model.Invoice;
import com.teachmeskills.documents.service.AssetService;
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
import org.example.common.dto.document.CreateInvoiceDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentFacade {

    private final DocumentService documentService;
    private final EmployeeService employeeService;
    private final OrganizationService organizationService;
    private final AssetService assetService;

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

    public Invoice createInvoice(CreateInvoiceDto dto) {
        Organization organization = organizationService
                .get(dto.getOrganization()).stream().findFirst().orElse(null);
        Employee fromEmployee = employeeService.get(dto.getFromEmployee()).stream().findFirst().orElse(null);
        Employee toEmployee = employeeService.get(dto.getToEmployee()).stream().findFirst().orElse(null);
        DocumentType documentType = documentService.getDocumentType(dto.getDocumentType()).stream().findFirst()
                .orElse(null);
        Department fromDepartment = organizationService.getDepartment(dto.getFromDepartment()).stream().findFirst()
                .orElse(null);
        Department toDepartment = organizationService.getDepartment(dto.getToDepartment()).stream().findFirst()
                .orElse(null);
        Asset asset = assetService.getAsset(dto.getAsset()).stream().findFirst().orElse(null);

        return documentService.createInvoice(dto.getDocumentNumber(), dto.getDocumentDate(), organization,
                fromDepartment, toDepartment, fromEmployee, toEmployee, documentType, asset);
    }
}
