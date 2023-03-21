package com.teachmeskills.documents.controller;

import com.teachmeskills.documents.converter.EmployeeConverter;
import com.teachmeskills.documents.facade.EmployeeFacade;
import com.teachmeskills.documents.model.Employee;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.common.dto.document.EmployeeDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
@Slf4j
public class EmployeeController {

    private final EmployeeFacade employeeFacade;
    private final EmployeeConverter employeeConverter;

    @GetMapping
    protected List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeFacade.getEmployees();
        return employeeConverter.toDto(employees);
    }

    @GetMapping("/{fullName}")
    protected EmployeeDto getEmployeeByName(@PathVariable("fullName") final String fullName) {
        Employee employee = employeeFacade.getEmployeeByName(fullName);
        return employeeConverter.toDto(employee);
    }
}
