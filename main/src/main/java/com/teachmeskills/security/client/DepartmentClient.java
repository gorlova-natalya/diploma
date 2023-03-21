package com.teachmeskills.security.client;

import org.example.common.dto.document.DepartmentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "domain7", url = "${services.document.url}/api/v1/departments")
public interface DepartmentClient {

    @RequestMapping(method = RequestMethod.GET)
    List<DepartmentDto> getDepartments();
}
