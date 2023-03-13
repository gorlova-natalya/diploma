package com.teachmeskills.documents.controller;

import com.teachmeskills.documents.converter.CashReceiptConverter;
import com.teachmeskills.documents.facade.CashReceiptFacade;
import com.teachmeskills.documents.model.CashReceipt;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.common.dto.document.CashReceiptDto;
import org.example.common.dto.document.CreateCashReceiptDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/cash-receipts")
@RequiredArgsConstructor
@Slf4j
public class CashReceiptController {

    private final CashReceiptFacade cashReceiptFacade;
    private final CashReceiptConverter cashReceiptConverter;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    protected CashReceiptDto createCashReceipt(@Valid @RequestBody final CreateCashReceiptDto dto) {
        final CashReceipt cashReceipt = cashReceiptFacade.createCashReceipt(dto);
        log.info("Document created, " + cashReceipt.getDocumentType().getTypeName() +
                ", №" + cashReceipt.getDocumentNumber());
        return cashReceiptConverter.toDto(cashReceipt);
    }
}
