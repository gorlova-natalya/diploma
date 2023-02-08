package com.teachmeskills.security.controller;

import com.teachmeskills.security.dto.CashReceiptDto;
import com.teachmeskills.security.dto.DocumentTypeDto;
import com.teachmeskills.security.dto.EmployeeDto;
import com.teachmeskills.security.dto.OrganizationDto;
import com.teachmeskills.security.service.CashReceiptService;
import com.teachmeskills.security.service.DocumentService;
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
@RequestMapping(value = "/cash-receipt")
@RequiredArgsConstructor
@Slf4j
public class CashReceiptController {

    private final CashReceiptService cashReceiptService;
    private final DocumentService documentService;

    @GetMapping("/{id}")
    protected String doGet(@PathVariable ("id") Long id, final Model model) {
        DocumentTypeDto documentType = documentService.getDocumentTypeById(id);
        model.addAttribute("documentType", documentType);
        List<OrganizationDto> organizations = cashReceiptService.getOrganizations();
        model.addAttribute("organizationsDto", organizations);
        List<EmployeeDto> employees = cashReceiptService.getEmployees();
        model.addAttribute("employeesDto", employees);
        model.addAttribute("cashDto", new CashReceiptDto());
        return "cashReceiptForm";
    }

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    protected String createOrder(@ModelAttribute("cashDto") CashReceiptDto cashReceiptDto, Model model)  {
        CashReceiptDto order = cashReceiptService.createOrder(cashReceiptDto);
        model.addAttribute("cashReceipt", order);
        return "cash";
    }
}
