package com.teachmeskills.documents.controller;

import com.teachmeskills.documents.converter.CashVoucherConverter;
import com.teachmeskills.documents.facade.CashVoucherFacade;
import com.teachmeskills.documents.model.CashVoucher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.common.dto.document.CashVoucherDto;
import org.example.common.dto.document.CreateCashVoucherDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/cash-vouchers")
@RequiredArgsConstructor
@Slf4j
public class CashVoucherController {

    private final CashVoucherFacade cashVoucherFacade;
    private final CashVoucherConverter cashVoucherConverter;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    protected CashVoucherDto createCashVoucher(@Valid @RequestBody final CreateCashVoucherDto dto) {
        final CashVoucher cashVoucher = cashVoucherFacade.createCashVoucher(dto);
        log.info("Document created, " + cashVoucher.getDocumentType().getTypeName() +
                ", №" + cashVoucher.getDocumentNumber());
        return cashVoucherConverter.toDto(cashVoucher);
    }
}
