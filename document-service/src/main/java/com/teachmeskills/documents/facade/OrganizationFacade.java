package com.teachmeskills.documents.facade;

import com.teachmeskills.documents.model.Department;
import com.teachmeskills.documents.model.Employee;
import com.teachmeskills.documents.model.Organization;
import com.teachmeskills.documents.service.DepartmentService;
import com.teachmeskills.documents.service.EmployeeService;
import com.teachmeskills.documents.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrganizationFacade {

    private final OrganizationService organizationService;

    private final EmployeeService employeeService;

    private final DepartmentService departmentService;

    public List<Organization> findOrganizations() {
        return organizationService.findOrganizations();
    }

    public Organization getOrganizationByName(String name) {
        return organizationService.getOrganizationByName(name).stream().findFirst().orElse(null);
    }

    public List<Employee> getEmployees() {
        return employeeService.getEmployees();
    }

    public Employee getEmployeeByName(String fullName) {
        return employeeService.getEmployeeByName(fullName).stream().findFirst().orElse(null);
    }

    public List<Department> findDepartments() {
        return departmentService.findDepartments();
    }
}
