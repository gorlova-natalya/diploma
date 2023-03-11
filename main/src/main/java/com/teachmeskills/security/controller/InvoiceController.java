package com.teachmeskills.security.controller;

import com.ibm.icu.text.RuleBasedNumberFormat;
import com.teachmeskills.security.config.ThymeLeafConfig;
import org.example.common.dto.document.AssetCountDto;
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
import org.thymeleaf.context.Context;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Locale;

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
    protected String createInvoice(@ModelAttribute("invoiceDto") CreateInvoiceDto createInvoiceDto, Model model) throws IOException {
        InvoiceDto invoice = documentService.createInvoice(createInvoiceDto);
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
        Writer writer = new FileWriter("C:/Users/natas/Documents/diploma/main/src/main/resources/filledTemplates/internal_invoice.html");
        writer.write(ThymeLeafConfig.getTemplateEngine().process("invoice.html", context));
        writer.close();

        String pdfFileName = "C:/Users/natas/Documents/diploma/print/internal_invoice.pdf";
        String htmlFileName = "C:/Users/natas/Documents/diploma/main/src/main/resources/filledTemplates/internal_invoice.html";

        documentService.generatePDF(pdfFileName, htmlFileName);
        return "invoice";
    }
}
