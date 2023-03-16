package com.teachmeskills.documents.facade;

import com.teachmeskills.documents.model.Employee;
import com.teachmeskills.documents.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeFacade {

    private final EmployeeService employeeService;

    public List<Employee> getEmployees() {
        return employeeService.getEmployees();
    }

    public Employee getEmployeeByName(String fullName) {
        return employeeService.getEmployeeByName(fullName).stream().findFirst().orElse(null);
    }
}
