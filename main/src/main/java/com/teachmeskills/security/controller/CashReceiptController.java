package com.teachmeskills.security.controller;

import com.teachmeskills.security.service.DocumentService;
import com.teachmeskills.security.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.example.common.dto.document.CashReceiptDto;
import org.example.common.dto.document.CreateCashReceiptDto;
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
@RequestMapping(value = "/cash-receipt")
@RequiredArgsConstructor
@Slf4j
public class CashReceiptController {

    private final OrganizationService organizationService;
    private final DocumentService documentService;

    @GetMapping("/{id}")
    protected String doGet(@PathVariable("id") Long id, final Model model) {
        List<OrganizationDto> organizations = organizationService.getOrganizations();
        model.addAttribute("organizationsDto", organizations);
        List<EmployeeDto> employees = organizationService.getEmployees();
        model.addAttribute("employeesDto", employees);
        model.addAttribute("cashDto", new CreateCashReceiptDto());
        model.addAttribute("documentTypeId", id);
        return "cashReceiptForm";
    }

    @SneakyThrows
    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    protected String createOrder(@ModelAttribute("cashDto") CreateCashReceiptDto createCashReceiptDto, Model model) {
        CashReceiptDto order = documentService.createOrder(createCashReceiptDto);
        model.addAttribute("cashReceipt", order);

//        documentService.generatePDF();
        return "cash";
    }

    @SneakyThrows
    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, value = "/pdf")
    protected String createPdf(@ModelAttribute("cashDto") CreateCashReceiptDto createCashReceiptDto,
                               @ModelAttribute("cashReceipt") CashReceiptDto order) {
        documentService.generatePDF();
        return "menu";
    }
}
