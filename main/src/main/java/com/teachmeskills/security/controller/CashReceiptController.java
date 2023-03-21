package com.teachmeskills.security.controller;

import com.ibm.icu.text.RuleBasedNumberFormat;
import com.teachmeskills.security.config.ThymeLeafConfig;
import com.teachmeskills.security.facade.CashReceiptFacade;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.example.common.dto.document.CashReceiptDto;
import org.example.common.dto.document.CreateCashReceiptDto;
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

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping(value = "/cash-receipt")
@RequiredArgsConstructor
@Slf4j
public class CashReceiptController {

    private final CashReceiptFacade cashReceiptFacade;

    @GetMapping("/{id}")
    protected String doGet(@PathVariable("id") Long id, final Model model) {
        List<OrganizationDto> organizations = cashReceiptFacade.getOrganizations();
        model.addAttribute("organizationsDto", organizations);
        List<EmployeeDto> employees = cashReceiptFacade.getEmployees();
        model.addAttribute("employeesDto", employees);
        model.addAttribute("cashDto", CreateCashReceiptDto.builder().build());
        model.addAttribute("documentTypeId", id);
        return "cashReceiptForm";
    }

    @SneakyThrows
    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    protected ResponseEntity<byte[]> createOrder(@Valid @ModelAttribute("cashDto") CreateCashReceiptDto createCashReceiptDto,
                                                 Model model) {
        CashReceiptDto order = cashReceiptFacade.createOrder(createCashReceiptDto);
        model.addAttribute("cashReceipt", order);

        RuleBasedNumberFormat nf = new RuleBasedNumberFormat(Locale.forLanguageTag("ru"),
                RuleBasedNumberFormat.SPELLOUT);
        String format = nf.format(order.getSum());
        model.addAttribute("sumText", format);

        Context context = new Context();
        context.setVariable("cashReceipt", order);
        context.setVariable("sumText", format);

        String stringFilledHtml = ThymeLeafConfig.getTemplateEngine().process("cash.html", context);

        return cashReceiptFacade.getPdf(stringFilledHtml, order.getDocumentNumber());
    }
}
