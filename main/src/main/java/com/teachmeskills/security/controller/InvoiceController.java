package com.teachmeskills.security.controller;

import com.ibm.icu.text.RuleBasedNumberFormat;
import com.teachmeskills.security.config.ThymeLeafConfig;
import com.teachmeskills.security.facade.InvoiceFacade;
import org.example.common.dto.document.AssetCountDto;
import org.example.common.dto.document.AssetDto;
import lombok.RequiredArgsConstructor;
import org.example.common.dto.document.CreateInvoiceDto;
import org.example.common.dto.document.DepartmentDto;
import org.example.common.dto.document.EmployeeDto;
import org.example.common.dto.document.InvoiceDto;
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
@RequestMapping(value = "/internal-invoice")
@RequiredArgsConstructor
public class InvoiceController {

    private final InvoiceFacade invoiceFacade;

    @GetMapping("/{id}")
    protected String doGet(@PathVariable("id") Long id, final Model model) {
        List<OrganizationDto> organizations = invoiceFacade.getOrganizations();
        List<DepartmentDto> departments = invoiceFacade.getDepartments();
        List<AssetDto> assets = invoiceFacade.getAssets();
        model.addAttribute("organizationsDto", organizations);
        model.addAttribute("departmentsDto", departments);
        model.addAttribute("assetsDto", assets);
        List<EmployeeDto> employees = invoiceFacade.getEmployees();
        model.addAttribute("employeesDto", employees);
        model.addAttribute("invoiceDto", new CreateInvoiceDto());
        model.addAttribute("documentTypeId", id);
        return "invoiceForm";
    }

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    protected ResponseEntity<byte[]> createInvoice(@ModelAttribute("invoiceDto") CreateInvoiceDto createInvoiceDto,
                                                   Model model) {
        InvoiceDto invoice = invoiceFacade.createInvoice(createInvoiceDto);
        double sumInvoice = invoice.getAssetCount().stream().mapToDouble(AssetCountDto::getSum).sum();
        long countOfAssets = invoice.getAssetCount().size();
        model.addAttribute("invoice", invoice);
        model.addAttribute("documentSum", sumInvoice);

        RuleBasedNumberFormat nf = new RuleBasedNumberFormat(Locale.forLanguageTag("ru"),
                RuleBasedNumberFormat.SPELLOUT);
        String format = nf.format(sumInvoice);
        String count = nf.format(countOfAssets);

        model.addAttribute("sumText", format);
        model.addAttribute("countText", count);

        Context context = new Context();
        context.setVariable("invoice", invoice);
        context.setVariable("sumText", format);
        context.setVariable("countText", count);
        context.setVariable("documentSum", sumInvoice);

        String stringFilledHtml = ThymeLeafConfig.getTemplateEngine().process("invoice.html", context);

        return invoiceFacade.getPdf(stringFilledHtml, invoice.getDocumentNumber());
    }
}
