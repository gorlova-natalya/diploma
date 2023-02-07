package com.teachmeskills.security.client;

import com.teachmeskills.security.dto.CashReceiptDto;
import com.teachmeskills.security.dto.DocumentTypeDto;
import com.teachmeskills.security.dto.EmployeeDto;
import com.teachmeskills.security.dto.OrganizationDto;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "domain1", url = "${services.document.url}/api/v1/documents")
public interface DocumentClient {

    @RequestMapping(method = RequestMethod.POST)
    @Headers(value = "Content-Type: application/json")
    CashReceiptDto createCashReceipt(@RequestBody final CashReceiptDto request);

    @RequestMapping(method = RequestMethod.GET, value = "/organizations")
    List<OrganizationDto> getAllOrganizations();

    @RequestMapping(method = RequestMethod.GET, value = "/type")
    @Headers(value = "Content-Type: application/json")
    DocumentTypeDto getDocumentTypeById(@RequestBody final long id);

    @RequestMapping(method = RequestMethod.GET, value = "/employees")
    List<EmployeeDto> getEmployees();

    @RequestMapping(method = RequestMethod.GET, value = "/types")
    List<DocumentTypeDto> getDocumentTypes();
}
