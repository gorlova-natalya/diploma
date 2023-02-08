package com.teachmeskills.documents.service;

import com.teachmeskills.documents.model.DocumentType;
import com.teachmeskills.documents.model.Employee;
import com.teachmeskills.documents.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public Employee getEmployeeByName(String name) {
        return employeeRepository.getEmployeeByFullName(name);
    }

    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    public List<Employee> getEmployeesBySignedDocumentsContains(DocumentType documentType) {
        return employeeRepository.getEmployeesBySignedDocuments(documentType);
    }
}
