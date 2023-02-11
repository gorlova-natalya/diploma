package com.teachmeskills.security.client;

import com.teachmeskills.security.dto.CashReceiptDto;
import com.teachmeskills.security.dto.CashVoucherDto;
import com.teachmeskills.security.dto.CreateCashReceiptDto;
import com.teachmeskills.security.dto.CreateCashVoucherDto;
import com.teachmeskills.security.dto.DocumentTypeDto;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "domain1", url = "${services.document.url}/api/v1/documents")
public interface DocumentClient {

    @RequestMapping(method = RequestMethod.POST, value = "/order")
    @Headers(value = "Content-Type: application/json")
    CashReceiptDto createCashReceipt(final CreateCashReceiptDto request);

    @RequestMapping(method = RequestMethod.POST, value = "/voucher")
    @Headers(value = "Content-Type: application/json")
    CashVoucherDto createCashVoucher(final CreateCashVoucherDto request);

    @RequestMapping(method = RequestMethod.GET, value = "/type")
    @Headers(value = "Content-Type: application/json")
    DocumentTypeDto getDocumentTypeById(final Long id);

    @RequestMapping(method = RequestMethod.GET, value = "/types")
    List<DocumentTypeDto> getDocumentTypes();
}
