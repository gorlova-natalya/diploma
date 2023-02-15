package com.teachmeskills.security.controller;

import com.teachmeskills.security.dto.CashVoucherDto;
import com.teachmeskills.security.dto.CreateCashVoucherDto;
import com.teachmeskills.security.dto.EmployeeDto;
import com.teachmeskills.security.dto.OrganizationDto;
import com.teachmeskills.security.service.DocumentService;
import com.teachmeskills.security.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class InvoiceController {

    private final DocumentService documentService;
    private final OrganizationService organizationService;

    @GetMapping("/{id}")
    protected String doGet(@PathVariable("id") Long id, final Model model) {
        List<OrganizationDto> organizations = organizationService.getOrganizations();
        model.addAttribute("organizationsDto", organizations);
        List<EmployeeDto> employees = organizationService.getEmployees();
        model.addAttribute("employeesDto", employees);
        model.addAttribute("invoiceDto", new CreateCashVoucherDto());
        model.addAttribute("documentTypeId", id);
        return "invoiceForm";
    }

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    protected String createCashVoucher(@ModelAttribute("invoiceDto") CreateCashVoucherDto createCashVoucherDto, Model model) {
        CashVoucherDto voucher = documentService.createVoucher(createCashVoucherDto);
        model.addAttribute("cashVoucher", voucher);
        return "voucher";
    }
}
