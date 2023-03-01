package com.teachmeskills.documents.controller;

import com.teachmeskills.documents.converter.InvoiceConverter;
import com.teachmeskills.documents.facade.DocumentFacade;
import com.teachmeskills.documents.model.Invoice;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.common.dto.document.CreateInvoiceDto;
import org.example.common.dto.document.InvoiceDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/invoices")
@RequiredArgsConstructor
@Slf4j
public class InvoiceController {

    private final DocumentFacade documentFacade;
    private final InvoiceConverter invoiceConverter;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    protected InvoiceDto createInvoice(@Valid @RequestBody final CreateInvoiceDto dto) {
        final Invoice invoice = documentFacade.createInvoice(dto);
        log.info("Document created, " + invoice.getDocumentType().getTypeName() +
                ", №" + invoice.getDocumentNumber());
        return invoiceConverter.toDto(invoice);
    }
}
