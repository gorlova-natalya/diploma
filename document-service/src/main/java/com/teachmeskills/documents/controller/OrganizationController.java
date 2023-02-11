package com.teachmeskills.documents.controller;

import com.teachmeskills.documents.converter.EmployeeConverter;
import com.teachmeskills.documents.converter.OrganizationConverter;
import com.teachmeskills.documents.dto.EmployeeDto;
import com.teachmeskills.documents.dto.OrganizationDto;
import com.teachmeskills.documents.model.Employee;
import com.teachmeskills.documents.model.Organization;
import com.teachmeskills.documents.service.EmployeeService;
import com.teachmeskills.documents.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/organizations")
@RequiredArgsConstructor
@Slf4j
public class OrganizationController {

    private final OrganizationService organizationService;
    private final OrganizationConverter organizationConverter;
    private final EmployeeService employeeService;
    private final EmployeeConverter employeeConverter;

    @GetMapping("/organizations")
    protected List<OrganizationDto> getAllOrganizations() {
        List<Organization> organizations = organizationService.findOrganizations();
        return organizationConverter.toDto(organizations);
    }

    @GetMapping("/organization")
    protected OrganizationDto getOrganization(@RequestBody final String name) {
        Organization organization = organizationService.getOrganizationByName(name).stream().findFirst().orElse(null);
        return organizationConverter.toDto(organization);
    }

    @GetMapping("/employees")
    protected List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeService.getEmployees();
        return employeeConverter.toDto(employees);
    }

    @GetMapping("/employee")
    protected EmployeeDto getEmployeeByName(@RequestBody final String fullName) {
        Employee employee = employeeService.getEmployeeByName(fullName).stream().findFirst().orElse(null);
        return employeeConverter.toDto(employee);
    }
}
