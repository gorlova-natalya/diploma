package com.teachmeskills;

import com.teachmeskills.security.client.OrganizationClient;
import com.teachmeskills.security.service.OrganizationService;
import org.example.common.dto.document.DepartmentDto;
import org.example.common.dto.document.EmployeeDto;
import org.example.common.dto.document.OrganizationDto;
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
@ContextConfiguration(classes = OrganizationService.class)
public class OrganizationServiceTest {

    @Autowired
    private OrganizationService organizationService;

    @MockBean
    private OrganizationClient organizationClient;

    @Test
    public void getAllOrganizationsTest() {
        List<OrganizationDto> organizationDtos = new ArrayList<>();

        given(organizationClient.getAllOrganizations()).willReturn(organizationDtos);
        List<OrganizationDto> expected = organizationService.getOrganizations();

        assertEquals(expected, organizationDtos);

        verify(organizationClient).getAllOrganizations();
    }

    @Test
    public void getAllEmployeesTest() {
        List<EmployeeDto> employees = new ArrayList<>();

        given(organizationClient.getEmployees()).willReturn(employees);
        List<EmployeeDto> expected = organizationService.getEmployees();

        assertEquals(expected, employees);

        verify(organizationClient).getEmployees();
    }

    @Test
    public void getAllDepartmentsTest() {
        List<DepartmentDto> departments = new ArrayList<>();

        given(organizationClient.getDepartments()).willReturn(departments);
        List<DepartmentDto> expected = organizationService.getDepartments();

        assertEquals(expected, departments);

        verify(organizationClient).getDepartments();
    }
}
