package com.teachmeskills.documents.facade;

import com.teachmeskills.documents.model.CashVoucher;
import com.teachmeskills.documents.model.DocumentType;
import com.teachmeskills.documents.model.Employee;
import com.teachmeskills.documents.model.Organization;
import com.teachmeskills.documents.service.CashVoucherService;
import com.teachmeskills.documents.service.DocumentTypeService;
import com.teachmeskills.documents.service.EmployeeService;
import com.teachmeskills.documents.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.example.common.dto.document.CreateCashVoucherDto;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class CashVoucherFacade {

    private final DocumentTypeService documentTypeService;
    private final CashVoucherService cashVoucherService;
    private final EmployeeService employeeService;
    private final OrganizationService organizationService;

    public CashVoucher createCashVoucher(CreateCashVoucherDto dto) {
        Organization organization = organizationService.get(dto.getOrganizationId()).orElse(null);
        Employee employee = employeeService.get(dto.getEmployeeId()).orElse(null);
        DocumentType documentType = documentTypeService.getDocumentType(dto.getDocumentTypeId()).orElse(null);

        return cashVoucherService.createCashVoucher(documentType,
                dto.getDocumentNumber(), dto.getPurpose(),
                LocalDate.parse(dto.getDocumentDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                employee, organization, dto.getSum(), dto.getAnnex(), dto.getPassport());
    }
}
