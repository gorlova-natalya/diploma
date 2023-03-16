package com.teachmeskills.security.facade;

import com.teachmeskills.security.service.DocumentService;
import com.teachmeskills.security.service.EmployeeService;
import com.teachmeskills.security.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.example.common.dto.document.CashVoucherDto;
import org.example.common.dto.document.CreateCashVoucherDto;
import org.example.common.dto.document.EmployeeDto;
import org.example.common.dto.document.OrganizationDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CashVoucherFacade {

    private final OrganizationService organizationService;
    private final DocumentService documentService;
    private final EmployeeService employeeService;

    public List<OrganizationDto> getOrganizations() {
        return organizationService.getOrganizations();
    }

    public List<EmployeeDto> getEmployees() {
        return employeeService.getEmployees();
    }

    public CashVoucherDto createVoucher(final CreateCashVoucherDto createCashVoucherDto) {
        return documentService.createVoucher(createCashVoucherDto);
    }

    public ResponseEntity<byte[]> getPdf(String stringFilledHtml, Integer documentNumber) {
        String pdfFileName = "cash_voucher_" + documentNumber + ".pdf";

        byte[] bytes = documentService.generatePDF(stringFilledHtml);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData(pdfFileName, pdfFileName);
        return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
    }
}
