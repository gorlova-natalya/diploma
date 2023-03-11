package com.teachmeskills.documents;

import com.teachmeskills.documents.model.Employee;
import com.teachmeskills.documents.model.Position;
import com.teachmeskills.documents.repository.EmployeeRepository;
import com.teachmeskills.documents.service.EmployeeService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@Disabled
@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = EmployeeService.class)
public class EmployeeServiceTest {

    @Autowired
    private EmployeeService employeeService;

    @MockBean
    private EmployeeRepository employeeRepository;

    @Test
    public void getAllEmployeesTest() {

        Employee employee1 = new Employee(1L, "Иванов Иван Иванович", new Position(), new ArrayList<>(),
                new ArrayList<>());
        Employee employee2 = new Employee(2L, "Петров Петр Петрович", new Position(), new ArrayList<>(),
                new ArrayList<>());
        List<Employee> allEmployees = List.of(employee1, employee2);

        given(employeeRepository.findAll()).willReturn(allEmployees);
        List<Employee> expected = employeeService.getEmployees();

        assertEquals(expected, allEmployees);

        verify(employeeRepository).findAll();
    }

    @Test
    public void getEmployeeByNameTest() {
        Employee employee = new Employee(1L, "Иванов Иван Иванович", new Position(), new ArrayList<>(),
                new ArrayList<>());

        final Optional<Employee> expected = Optional.of(employee);

        given(employeeRepository.getEmployeeByFullName(employee.getFullName())).willReturn(expected);

        Optional<Employee> returned = employeeService.getEmployeeByName(employee.getFullName());

        assertEquals(expected, returned);

        verify(employeeRepository).getEmployeeByFullName(employee.getFullName());
    }

    @Test
    public void getEmployeeByIdTest() {
        Employee employee = new Employee(1L, "Иванов Иван Иванович", new Position(), new ArrayList<>(),
                new ArrayList<>());

        final Optional<Employee> expected = Optional.of(employee);

        given(employeeRepository.getEmployeeById(employee.getId())).willReturn(expected);

        Optional<Employee> returned = employeeService.get(employee.getId());

        assertEquals(expected, returned);

        verify(employeeRepository).getEmployeeById(employee.getId());
    }
}
