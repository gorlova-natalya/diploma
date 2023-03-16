package com.teachmeskills.security.service;

import com.teachmeskills.security.client.EmployeeClient;
import lombok.RequiredArgsConstructor;
import org.example.common.dto.document.EmployeeDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeClient employeeClient;

    public List<EmployeeDto> getEmployees() {
        return employeeClient.getEmployees();
    }
}
