package com.teachmeskills.security.facade;

import com.teachmeskills.security.service.AssetService;
import com.teachmeskills.security.service.DepartmentService;
import com.teachmeskills.security.service.DocumentService;
import com.teachmeskills.security.service.EmployeeService;
import com.teachmeskills.security.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.example.common.dto.document.AssetDto;
import org.example.common.dto.document.CreateInvoiceDto;
import org.example.common.dto.document.DepartmentDto;
import org.example.common.dto.document.EmployeeDto;
import org.example.common.dto.document.InvoiceDto;
import org.example.common.dto.document.OrganizationDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InvoiceFacade {

    private final OrganizationService organizationService;
    private final DocumentService documentService;
    private final EmployeeService employeeService;
    private final AssetService assetService;
    private final DepartmentService departmentService;

    public List<OrganizationDto> getOrganizations() {
        return organizationService.getOrganizations();
    }

    public List<EmployeeDto> getEmployees() {
        return employeeService.getEmployees();
    }

    public InvoiceDto createInvoice(final CreateInvoiceDto createInvoiceDto) {
        return documentService.createInvoice(createInvoiceDto);
    }

    public List<DepartmentDto> getDepartments() {
        return departmentService.getDepartments();
    }

    public List<AssetDto> getAssets() {
        return assetService.getAssets();
    }

    public ResponseEntity<byte[]> getPdf(String stringFilledHtml, Integer documentNumber) {
        String pdfFileName = "internal_invoice_" + documentNumber + ".pdf";

        byte[] bytes = documentService.generatePDF(stringFilledHtml);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData(pdfFileName, pdfFileName);
        return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
    }
}
