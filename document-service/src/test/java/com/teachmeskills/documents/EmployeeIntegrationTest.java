package com.teachmeskills.documents;

import com.teachmeskills.documents.model.Employee;
import com.teachmeskills.documents.repository.EmployeeRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeeIntegrationTest extends AbstractIntegrationDocumentsTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void getEmployeeByFullNameTest() {
        String name = "Акудович Елена Георгиевна";

        Optional<Employee> result = employeeRepository.getEmployeeByFullName(name);

        assertEquals(result.get().getFullName(), name);
    }

    @Test
    public void getEmployeeByIdTest() {
        Employee employee = new Employee();
        employee.setId(1L);

        Optional<Employee> result = employeeRepository.getEmployeeById(employee.getId());

        assertEquals(result.get().getPosition().getName(), "Директор");
    }

    @Test
    public void findUsersTest() {
        List<Employee> employees = employeeRepository.findAll();

        assertThat(employees)
                .hasSize(7);
    }
}
