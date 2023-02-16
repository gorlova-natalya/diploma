package com.teachmeskills.documents.controller;

import com.teachmeskills.documents.converter.CashReceiptConverter;
import com.teachmeskills.documents.converter.CashVoucherConverter;
import com.teachmeskills.documents.converter.DocumentTypeConverter;
import org.example.common.dto.document.CashReceiptDto;
import org.example.common.dto.document.CashVoucherDto;
import org.example.common.dto.document.CreateCashReceiptDto;
import org.example.common.dto.document.CreateCashVoucherDto;
import com.teachmeskills.documents.facade.DocumentFacade;
import com.teachmeskills.documents.model.CashReceipt;
import com.teachmeskills.documents.model.CashVoucher;
import com.teachmeskills.documents.model.DocumentType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.common.dto.document.DocumentTypeDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/documents")
@RequiredArgsConstructor
@Slf4j
public class DocumentsController {

    private final DocumentFacade documentFacade;
    private final DocumentTypeConverter documentTypeConverter;
    private final CashReceiptConverter cashReceiptConverter;
    private final CashVoucherConverter cashVoucherConverter;

    @GetMapping("/type")
    protected DocumentTypeDto getDocumentTypeById(@RequestBody final Long id) {
        DocumentType documentType = documentFacade.getDocumentType(id);
        return documentTypeConverter.toDto(documentType);
    }

    @GetMapping("/types")
    protected List<DocumentTypeDto> getDocumentTypes() {
        List<DocumentType> documentTypes = documentFacade.getDocumentTypes();
        return documentTypeConverter.toDto(documentTypes);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, value = "/order")
    @ResponseStatus(HttpStatus.ACCEPTED)
    protected CashReceiptDto createCashReceipt(@Valid @RequestBody final CreateCashReceiptDto dto) {
        final CashReceipt cashReceipt = documentFacade.createCashReceipt(dto);
        log.info("документ создан");
        return cashReceiptConverter.toDto(cashReceipt);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, value = "/voucher")
    @ResponseStatus(HttpStatus.ACCEPTED)
    protected CashVoucherDto createCashVoucher(@Valid @RequestBody final CreateCashVoucherDto dto) {
        final CashVoucher cashVoucher = documentFacade.createCashVoucher(dto);
        log.info("документ создан");
        return cashVoucherConverter.toDto(cashVoucher);
    }
}
