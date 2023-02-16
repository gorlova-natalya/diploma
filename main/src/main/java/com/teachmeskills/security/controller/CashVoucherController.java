package com.teachmeskills.security.controller;

import org.example.common.dto.document.CashVoucherDto;
import com.teachmeskills.security.service.DocumentService;
import com.teachmeskills.security.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.common.dto.document.CreateCashVoucherDto;
import org.example.common.dto.document.EmployeeDto;
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
@RequestMapping(value = "/cash-voucher")
@RequiredArgsConstructor
@Slf4j
public class CashVoucherController {

    private final DocumentService documentService;
    private final OrganizationService organizationService;

    @GetMapping("/{id}")
    protected String doGet(@PathVariable("id") Long id, final Model model) {
        List<OrganizationDto> organizations = organizationService.getOrganizations();
        model.addAttribute("organizationsDto", organizations);
        List<EmployeeDto> employees = organizationService.getEmployees();
        model.addAttribute("employeesDto", employees);
        model.addAttribute("voucherDto", new CreateCashVoucherDto());
        model.addAttribute("documentTypeId", id);
        return "cashVoucherForm";
    }

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    protected String createCashVoucher(@ModelAttribute("voucherDto") CreateCashVoucherDto createCashVoucherDto, Model model) {
        CashVoucherDto voucher = documentService.createVoucher(createCashVoucherDto);
        model.addAttribute("cashVoucher", voucher);
        return "voucher";
    }
}
