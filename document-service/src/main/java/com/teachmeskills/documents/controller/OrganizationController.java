package com.teachmeskills.documents.controller;

import com.teachmeskills.documents.converter.DepartmentConverter;
import com.teachmeskills.documents.converter.EmployeeConverter;
import com.teachmeskills.documents.converter.OrganizationConverter;
import com.teachmeskills.documents.facade.OrganizationFacade;
import org.example.common.dto.document.DepartmentDto;
import org.example.common.dto.document.EmployeeDto;
import org.example.common.dto.document.OrganizationDto;
import com.teachmeskills.documents.model.Department;
import com.teachmeskills.documents.model.Employee;
import com.teachmeskills.documents.model.Organization;
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

    private final OrganizationFacade organizationFacade;
    private final OrganizationConverter organizationConverter;
    private final EmployeeConverter employeeConverter;
    private final DepartmentConverter departmentConverter;

    @GetMapping
    protected List<OrganizationDto> getAllOrganizations() {
        List<Organization> organizations = organizationFacade.findOrganizations();
        return organizationConverter.toDto(organizations);
    }

    @GetMapping("/organization")
    protected OrganizationDto getOrganization(@RequestBody final String name) {
        Organization organization = organizationFacade.getOrganizationByName(name);
        return organizationConverter.toDto(organization);
    }

    @GetMapping("/employees")
    protected List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = organizationFacade.getEmployees();
        return employeeConverter.toDto(employees);
    }

    @GetMapping("/employee")
    protected EmployeeDto getEmployeeByName(@RequestBody final String fullName) {
        Employee employee = organizationFacade.getEmployeeByName(fullName);
        return employeeConverter.toDto(employee);
    }

    @GetMapping("/departments")
    protected List<DepartmentDto> getDepartments() {
        List<Department> departments = organizationFacade.findDepartments();
        return departmentConverter.toDto(departments);
    }
}
