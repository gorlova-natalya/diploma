package com.teachmeskills.documents.facade;

import com.teachmeskills.documents.model.AssetCount;
import com.teachmeskills.documents.model.Department;
import com.teachmeskills.documents.model.Invoice;
import com.teachmeskills.documents.service.AssetService;
import com.teachmeskills.documents.service.CashReceiptService;
import com.teachmeskills.documents.service.CashVoucherService;
import com.teachmeskills.documents.service.InvoiceService;
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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DocumentFacade {

    private final DocumentService documentService;
    private final CashReceiptService cashReceiptService;
    private final CashVoucherService cashVoucherService;
    private final InvoiceService invoiceService;
    private final EmployeeService employeeService;
    private final OrganizationService organizationService;
    private final AssetService assetService;

    public CashReceipt createCashReceipt(CreateCashReceiptDto dto) {
        Organization organization = organizationService.get(dto.getOrganizationId()).orElse(null);
        Employee employee = employeeService.get(dto.getEmployeeId()).orElse(null);
        DocumentType documentType = documentService.getDocumentType(dto.getDocumentTypeId()).orElse(null);

        return cashReceiptService.createCashReceipt(documentType,
                dto.getDocumentNumber(), dto.getPurpose(), dto.getDocumentDate(),
                employee, organization, dto.getSum(), dto.getAnnex());
    }

    public CashVoucher createCashVoucher(CreateCashVoucherDto dto) {
        Organization organization = organizationService.get(dto.getOrganizationId()).orElse(null);
        Employee employee = employeeService.get(dto.getEmployeeId()).orElse(null);
        DocumentType documentType = documentService.getDocumentType(dto.getDocumentTypeId()).orElse(null);

        return cashVoucherService.createCashVoucher(documentType,
                dto.getDocumentNumber(), dto.getPurpose(), dto.getDocumentDate(),
                employee, organization, dto.getSum(), dto.getAnnex(), dto.getPassport());
    }

    public DocumentType getDocumentType(Long id) {
        return documentService.getDocumentType(id).orElse(null);
    }

    public List<DocumentType> getDocumentTypes() {
        return documentService.getDocumentTypes();
    }

    public Invoice createInvoice(CreateInvoiceDto dto) {
        Organization organization = organizationService.get(dto.getOrganization()).orElse(null);
        Employee fromEmployee = employeeService.get(dto.getFromEmployee()).orElse(null);
        Employee toEmployee = employeeService.get(dto.getToEmployee()).orElse(null);
        DocumentType documentType = documentService.getDocumentType(dto.getDocumentTypeId()).orElse(null);
        Department fromDepartment = organizationService.getDepartment(dto.getFromDepartment()).orElse(null);
        Department toDepartment = organizationService.getDepartment(dto.getToDepartment()).orElse(null);
        List<AssetCount> assets = dto.getAssetCount().stream()
                .filter(chooseAssetDto -> chooseAssetDto.getAssetId() != null)
                .map(chooseAssetDto -> AssetCount.builder()
                                .asset(assetService.getAsset(chooseAssetDto.getAssetId())
                                        .orElseThrow(RuntimeException::new))
                                .count(chooseAssetDto.getCount())
                                .sum(assetService.getSum(chooseAssetDto.getAssetId(), chooseAssetDto.getCount()))
                                .build())
                .collect(Collectors.toList());

        return invoiceService.createInvoice(dto.getDocumentNumber(), dto.getDocumentDate(), organization,
                fromDepartment, toDepartment, fromEmployee, toEmployee, documentType, assets);
    }
}
