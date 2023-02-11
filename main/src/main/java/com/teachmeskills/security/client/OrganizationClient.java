package com.teachmeskills.security.client;

import com.teachmeskills.security.dto.EmployeeDto;
import com.teachmeskills.security.dto.OrganizationDto;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "domain2", url = "${services.document.url}/api/v1/organizations")
public interface OrganizationClient {

    @RequestMapping(method = RequestMethod.GET, value = "/organizations")
    List<OrganizationDto> getAllOrganizations();

    @RequestMapping(method = RequestMethod.GET, value = "/organization")
    @Headers(value = "Content-Type: application/json")
    OrganizationDto getOrganization(final String name);

    @RequestMapping(method = RequestMethod.GET, value = "/employee")
    @Headers(value = "Content-Type: application/json")
    EmployeeDto getEmployeeByName(final String fullName);

    @RequestMapping(method = RequestMethod.GET, value = "/employees")
    List<EmployeeDto> getEmployees();
}
