package com.teachmeskills.security.client;

import feign.Headers;
import org.example.common.dto.document.CreateInvoiceDto;
import org.example.common.dto.document.InvoiceDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "domain6", url = "${services.document.url}/api/v1/invoices")
public interface InvoiceClient {

    @RequestMapping(method = RequestMethod.POST)
    @Headers(value = "Content-Type: application/json")
    InvoiceDto createInvoice(final CreateInvoiceDto createInvoiceDto);
}
