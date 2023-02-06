package com.teachmeskills.security.controller;

import com.teachmeskills.security.dto.CashReceiptDto;
import com.teachmeskills.security.dto.EmployeeDto;
import com.teachmeskills.security.dto.OrganizationDto;
import com.teachmeskills.security.service.CashReceiptService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/cash-receipt")
@RequiredArgsConstructor
@Slf4j
public class CashReceiptController {

    private final CashReceiptService cashReceiptService;

    @GetMapping
    protected String doGet(final Model model) {
        model.addAttribute("cashDto", new CashReceiptDto());
        List<OrganizationDto> organizations = cashReceiptService.getOrganizations();
        model.addAttribute("organizationsDto", organizations);
        List<EmployeeDto> employees = cashReceiptService.getEmployees();
        model.addAttribute("employeesDto", employees);
        return "cashReceiptForm";
    }

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    protected String createOrder(@ModelAttribute("cashReceiptDto") final CashReceiptDto cashReceiptDto, Model model) {
        CashReceiptDto order = cashReceiptService.createOrder(cashReceiptDto);
        model.addAttribute("cashReceipt", order);
        return "cash";
    }
}
