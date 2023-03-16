package com.teachmeskills.security.client;

import feign.Headers;
import org.example.common.dto.document.EmployeeDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "domain1", url = "${services.document.url}/api/v1/employees")
public interface EmployeeClient {

    @RequestMapping(method = RequestMethod.GET, value = "/{fullName}")
    @Headers(value = "Content-Type: application/json")
    EmployeeDto getEmployeeByName(@PathVariable("fullName") final String fullName);

    @RequestMapping(method = RequestMethod.GET, value = "/all")
    List<EmployeeDto> getEmployees();
}
