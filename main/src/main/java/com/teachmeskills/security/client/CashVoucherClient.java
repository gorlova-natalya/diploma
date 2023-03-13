package com.teachmeskills.security.client;

import feign.Headers;
import org.example.common.dto.document.CashVoucherDto;
import org.example.common.dto.document.CreateCashVoucherDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "domain5", url = "${services.document.url}/api/v1/cash-vouchers")
public interface CashVoucherClient {

    @RequestMapping(method = RequestMethod.POST)
    @Headers(value = "Content-Type: application/json")
    CashVoucherDto createCashVoucher(final CreateCashVoucherDto request);
}
