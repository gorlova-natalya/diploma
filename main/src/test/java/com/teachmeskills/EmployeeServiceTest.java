package com.teachmeskills;

import com.teachmeskills.security.client.EmployeeClient;
import com.teachmeskills.security.service.EmployeeService;
import org.example.common.dto.document.EmployeeDto;
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
    private EmployeeClient employeeClient;

    @Test
    public void getAllEmployeesTest() {
        List<EmployeeDto> employees = new ArrayList<>();

        given(employeeClient.getEmployees()).willReturn(employees);
        List<EmployeeDto> expected = employeeService.getEmployees();

        assertEquals(expected, employees);

        verify(employeeClient).getEmployees();
    }
}
