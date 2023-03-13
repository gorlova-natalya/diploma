package com.teachmeskills.security.controller;

import com.ibm.icu.text.RuleBasedNumberFormat;
import com.teachmeskills.security.config.ThymeLeafConfig;
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
import org.thymeleaf.context.Context;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Locale;

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
        model.addAttribute("voucherDto", CreateCashVoucherDto.builder().build());
        model.addAttribute("documentTypeId", id);
        return "cashVoucherForm";
    }

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    protected String createCashVoucher(@ModelAttribute("voucherDto") CreateCashVoucherDto createCashVoucherDto,
                                       Model model) throws IOException {
        CashVoucherDto voucher = documentService.createVoucher(createCashVoucherDto);
        model.addAttribute("cashVoucher", voucher);
        RuleBasedNumberFormat nf = new RuleBasedNumberFormat(Locale.forLanguageTag("ru"),
                RuleBasedNumberFormat.SPELLOUT);
        String format = nf.format(voucher.getSum());
        model.addAttribute("sumText", format);

        Context context = new Context();
        context.setVariable("cashVoucher", voucher);
        context.setVariable("sumText", format);
        Writer writer = new FileWriter("C:/Users/natas/Documents/diploma/main/src/main/resources/filledTemplates/cash_voucher.html");
        writer.write(ThymeLeafConfig.getTemplateEngine().process("voucher.html", context));
        writer.close();

        String pdfFileName = "C:/Users/natas/Documents/diploma/print/cash_voucher.pdf";
        String htmlFileName = "C:/Users/natas/Documents/diploma/main/src/main/resources/filledTemplates/cash_voucher.html";

        documentService.generatePDF(pdfFileName, htmlFileName);
        return "voucher";
    }
}
