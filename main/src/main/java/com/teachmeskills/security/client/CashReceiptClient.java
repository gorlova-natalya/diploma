package com.teachmeskills.security.client;

import feign.Headers;
import org.example.common.dto.document.CashReceiptDto;
import org.example.common.dto.document.CreateCashReceiptDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "domain4", url = "${services.document.url}/api/v1/cash-receipts")
public interface CashReceiptClient {

    @RequestMapping(method = RequestMethod.POST)
    @Headers(value = "Content-Type: application/json")
    CashReceiptDto createCashReceipt(final CreateCashReceiptDto request);
}
