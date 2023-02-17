package com.teachmeskills.security.controller;

import org.example.common.dto.document.AssetDto;
import com.teachmeskills.security.service.AssetService;
import com.teachmeskills.security.service.DocumentService;
import com.teachmeskills.security.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.example.common.dto.document.CreateInvoiceDto;
import org.example.common.dto.document.DepartmentDto;
import org.example.common.dto.document.EmployeeDto;
import org.example.common.dto.document.InvoiceDto;
import org.example.common.dto.document.OrganizationDto;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/internal-invoice")
@RequiredArgsConstructor
public class InvoiceController {

    private final DocumentService documentService;
    private final OrganizationService organizationService;
    private final AssetService assetService;

    @GetMapping("/{id}")
    protected String doGet(@PathVariable("id") Long id, final Model model) {
        List<OrganizationDto> organizations = organizationService.getOrganizations();
        List<DepartmentDto> departments = organizationService.getDepartments();
        List<AssetDto> assets = assetService.getAssets();
        model.addAttribute("organizationsDto", organizations);
        model.addAttribute("departmentsDto", departments);
        model.addAttribute("assetsDto", assets);
        List<EmployeeDto> employees = organizationService.getEmployees();
        model.addAttribute("employeesDto", employees);
        model.addAttribute("invoiceDto", new CreateInvoiceDto());
        model.addAttribute("documentTypeId", id);
        return "invoiceForm";
    }

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    protected String createInvoice(@ModelAttribute("invoiceDto") CreateInvoiceDto createInvoiceDto, Model model) {
        InvoiceDto invoice = documentService.createInvoice(createInvoiceDto);
        model.addAttribute("invoice", invoice);
        return "invoice";
    }
}
