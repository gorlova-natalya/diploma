package com.teachmeskills.security.controller;

import com.ibm.icu.text.RuleBasedNumberFormat;
import com.teachmeskills.security.config.ThymeLeafConfig;
import com.teachmeskills.security.facade.CashVoucherFacade;
import org.example.common.dto.document.CashVoucherDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.common.dto.document.CreateCashVoucherDto;
import org.example.common.dto.document.EmployeeDto;
import org.example.common.dto.document.OrganizationDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.context.Context;

import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping(value = "/cash-voucher")
@RequiredArgsConstructor
@Slf4j
public class CashVoucherController {

    private final CashVoucherFacade cashVoucherFacade;

    @GetMapping("/{id}")
    protected String doGet(@PathVariable("id") Long id, final Model model) {
        List<OrganizationDto> organizations = cashVoucherFacade.getOrganizations();
        model.addAttribute("organizationsDto", organizations);
        List<EmployeeDto> employees = cashVoucherFacade.getEmployees();
        model.addAttribute("employeesDto", employees);
        model.addAttribute("voucherDto", CreateCashVoucherDto.builder().build());
        model.addAttribute("documentTypeId", id);
        return "cashVoucherForm";
    }

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    protected ResponseEntity<byte[]> createCashVoucher(
            @ModelAttribute("voucherDto") CreateCashVoucherDto createCashVoucherDto,
            Model model) {
        CashVoucherDto voucher = cashVoucherFacade.createVoucher(createCashVoucherDto);
        model.addAttribute("cashVoucher", voucher);
        RuleBasedNumberFormat nf = new RuleBasedNumberFormat(Locale.forLanguageTag("ru"),
                RuleBasedNumberFormat.SPELLOUT);
        String format = nf.format(voucher.getSum());
        model.addAttribute("sumText", format);

        Context context = new Context();
        context.setVariable("cashVoucher", voucher);
        context.setVariable("sumText", format);

        String stringFilledHtml = ThymeLeafConfig.getTemplateEngine().process("voucher.html", context);

        return cashVoucherFacade.getPdf(stringFilledHtml, voucher.getDocumentNumber());
    }
}
