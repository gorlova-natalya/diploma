package com.teachmeskills.documents.service;

import com.teachmeskills.documents.model.Employee;
import com.teachmeskills.documents.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public Optional<Employee> getEmployeeByName(String fullName) {
        return employeeRepository.getEmployeeByFullName(fullName);
    }

    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> get(Long id) {
        return employeeRepository.getEmployeeById(id);
    }
}
