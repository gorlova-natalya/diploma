package com.teachmeskills.documents.controller;

import com.teachmeskills.documents.converter.DocumentTypeConverter;
import com.teachmeskills.documents.converter.EmployeeConverter;
import com.teachmeskills.documents.converter.OrganizationConverter;
import com.teachmeskills.documents.dto.CashReceiptDto;
import com.teachmeskills.documents.dto.DocumentTypeDto;
import com.teachmeskills.documents.dto.EmployeeDto;
import com.teachmeskills.documents.dto.OrganizationDto;
import com.teachmeskills.documents.facade.DocumentFacade;
import com.teachmeskills.documents.model.CashReceipt;
import com.teachmeskills.documents.model.DocumentType;
import com.teachmeskills.documents.model.Employee;
import com.teachmeskills.documents.model.Organization;
import com.teachmeskills.documents.service.EmployeeService;
import com.teachmeskills.documents.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/documents")
@RequiredArgsConstructor
@Slf4j
public class CashReceiptController {

    private final DocumentFacade documentFacade;
    private final OrganizationService organizationService;
    private final EmployeeService employeeService;
    private final OrganizationConverter organizationConverter;
    private final DocumentTypeConverter documentTypeConverter;
    private final EmployeeConverter employeeConverter;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    protected CashReceiptDto createCashReceipt(@Valid @RequestBody final CashReceiptDto dto) {
        final CashReceipt cashReceipt = documentFacade.createCashReceipt(dto.getDocumentType(), dto.getDocumentNumber(),
                dto.getPurpose(), dto.getDocumentDate(), dto.getEmployee(),
                dto.getOrganization(), dto.getSum(),
                dto.getAnnex());
        log.info("документ создан");
        CashReceiptDto cashReceiptDto = new CashReceiptDto();
        cashReceiptDto.setDocumentType(documentTypeConverter.toDto(cashReceipt.getDocumentType()));
        cashReceiptDto.setDocumentNumber(cashReceipt.getDocumentNumber());
        cashReceiptDto.setDocumentDate(cashReceipt.getDocumentDate());
        cashReceiptDto.setPurpose(cashReceipt.getPurpose());
        cashReceiptDto.setEmployee(employeeConverter.toDto(cashReceipt.getEmployee()));
        cashReceiptDto.setOrganization(organizationConverter.toDto(cashReceipt.getOrganization()));
        cashReceiptDto.setSum(cashReceipt.getSum());
        cashReceiptDto.setAnnex(cashReceipt.getAnnex());
        return cashReceiptDto;
    }

    @GetMapping("/organizations")
    protected List<OrganizationDto> getAllOrganizations() {
        List<Organization> organizations = organizationService.findOrganizations();
        return organizationConverter.toDto(organizations);
    }

    @GetMapping("/employees")
    protected List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeService.getEmployees();
        return employeeConverter.toDto(employees);
    }

    @GetMapping("/type")
    protected DocumentTypeDto getDocumentTypeById(@RequestBody final long id) {
        DocumentType documentType = documentFacade.getDocumentType(id);
        return documentTypeConverter.toDto(documentType);
    }

    @GetMapping("/types")
    protected List<DocumentTypeDto> getDocumentTypes() {
        List<DocumentType> documentTypes = documentFacade.getDocumentTypes();
        return documentTypeConverter.toDto(documentTypes);
    }
}
