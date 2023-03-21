package com.teachmeskills.security.client;

import feign.Headers;
import org.example.common.dto.document.OrganizationDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "domain2", url = "${services.document.url}/api/v1/organizations")
public interface OrganizationClient {

    @RequestMapping(method = RequestMethod.GET)
    List<OrganizationDto> getAllOrganizations();

    @RequestMapping(method = RequestMethod.GET, value = "/{name}")
    @Headers(value = "Content-Type: application/json")
    OrganizationDto getOrganization(@PathVariable("name") final String name);
}
