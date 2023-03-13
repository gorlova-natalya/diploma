package com.teachmeskills.documents.facade;

import com.teachmeskills.documents.model.AssetCount;
import com.teachmeskills.documents.model.Department;
import com.teachmeskills.documents.model.DocumentType;
import com.teachmeskills.documents.model.Employee;
import com.teachmeskills.documents.model.Invoice;
import com.teachmeskills.documents.model.Organization;
import com.teachmeskills.documents.service.AssetService;
import com.teachmeskills.documents.service.DepartmentService;
import com.teachmeskills.documents.service.DocumentTypeService;
import com.teachmeskills.documents.service.EmployeeService;
import com.teachmeskills.documents.service.InvoiceService;
import com.teachmeskills.documents.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.example.common.dto.document.CreateInvoiceDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InvoiceFacade {

    private final DocumentTypeService documentTypeService;
    private final InvoiceService invoiceService;
    private final EmployeeService employeeService;
    private final OrganizationService organizationService;
    private final DepartmentService departmentService;
    private final AssetService assetService;

    public Invoice createInvoice(CreateInvoiceDto dto) {
        Organization organization = organizationService.get(dto.getOrganization()).orElse(null);
        Employee fromEmployee = employeeService.get(dto.getFromEmployee()).orElse(null);
        Employee toEmployee = employeeService.get(dto.getToEmployee()).orElse(null);
        DocumentType documentType = documentTypeService.getDocumentType(dto.getDocumentTypeId()).orElse(null);
        Department fromDepartment = departmentService.getDepartment(dto.getFromDepartment()).orElse(null);
        Department toDepartment = departmentService.getDepartment(dto.getToDepartment()).orElse(null);
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
