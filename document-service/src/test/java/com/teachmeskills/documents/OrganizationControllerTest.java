package com.teachmeskills.documents;

import com.teachmeskills.documents.controller.OrganizationController;
import com.teachmeskills.documents.converter.DepartmentConverter;
import com.teachmeskills.documents.converter.EmployeeConverter;
import com.teachmeskills.documents.converter.OrganizationConverter;
import com.teachmeskills.documents.facade.OrganizationFacade;
import com.teachmeskills.documents.model.Department;
import com.teachmeskills.documents.model.Employee;
import com.teachmeskills.documents.model.Organization;
import com.teachmeskills.documents.model.Position;
import org.example.common.dto.document.DepartmentDto;
import org.example.common.dto.document.EmployeeDto;
import org.example.common.dto.document.OrganizationDto;
import org.example.common.dto.document.PositionDto;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.testcontainers.shaded.com.fasterxml.jackson.core.type.TypeReference;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Disabled
@ExtendWith(SpringExtension.class)
@WebMvcTest(OrganizationController.class)
@AutoConfigureMockMvc(addFilters = false)
@ContextConfiguration(classes = OrganizationController.class)
public class OrganizationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper mapper = new ObjectMapper();

    @MockBean
    private OrganizationFacade organizationFacade;

    @MockBean
    private OrganizationConverter organizationConverter;

    @MockBean
    private EmployeeConverter employeeConverter;

    @MockBean
    private DepartmentConverter departmentConverter;

    @Test
    public void getAllOrganizationsTest() throws Exception {

        Organization organization1 = new Organization(1L, "OAO", 1234567, new Employee());
        Organization organization2 = new Organization(2L, "МТЗ", 1234569, new Employee());
        List<Organization> allOrganizations = List.of(organization1, organization2);

        List<OrganizationDto> expected = allOrganizations.stream().map(org -> new OrganizationDto(org.getId(), org.getName(),
                        org.getPayerNumber(), new EmployeeDto(1L, "Сотрудник", new PositionDto())))
                .collect(Collectors.toList());

        when(organizationFacade.findOrganizations()).thenReturn(allOrganizations);
        when(organizationConverter.toDto(allOrganizations)).thenReturn(expected);

        MvcResult mvcResult = mockMvc.perform(get("/api/v1/organizations")
                        .content(mapper.writeValueAsString(allOrganizations))
                        .contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        then(organizationFacade)
                .should()
                .findOrganizations();

        List<OrganizationDto> returned = mapper.readValue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8),
                new TypeReference<List<OrganizationDto>>() {
                });
        assertEquals(expected, returned);
    }

    @Test
    public void getOrganizationByNameTest() throws Exception {
        String orgName = "OAO";
        Organization organization = new Organization(1L, orgName, 1234567, new Employee());

        OrganizationDto expected = new OrganizationDto(organization.getId(), organization.getName(),
                organization.getPayerNumber(), new EmployeeDto(1L, "Сотрудник", new PositionDto()));

        when(organizationFacade.getOrganizationByName(orgName)).thenReturn(organization);
        when(organizationConverter.toDto(organization)).thenReturn(expected);

        MvcResult mvcResult = mockMvc.perform(get("/api/v1/organizations/organization")
                        .content(orgName)
                        .contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        then(organizationFacade)
                .should()
                .getOrganizationByName(orgName);

        OrganizationDto returned = mapper.readValue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8),
                new TypeReference<OrganizationDto>() {
                });
        assertEquals(expected, returned);
    }

    @Test
    public void getAllEmployeesTest() throws Exception {

        Employee employee1 = new Employee(1L, "Иванов Иван Иванович", new Position(), new ArrayList<>(),
                new ArrayList<>());
        Employee employee2 = new Employee(2L, "Петров Петр Петрович", new Position(), new ArrayList<>(),
                new ArrayList<>());
        List<Employee> allEmployees = List.of(employee1, employee2);

        List<EmployeeDto> expected = allEmployees.stream()
                .map(emp -> new EmployeeDto(emp.getId(), emp.getFullName(), new PositionDto()))
                .collect(Collectors.toList());

        when(organizationFacade.getEmployees()).thenReturn(allEmployees);
        when(employeeConverter.toDto(allEmployees)).thenReturn(expected);

        MvcResult mvcResult = mockMvc.perform(get("/api/v1/organizations/employees")
                        .content(mapper.writeValueAsString(allEmployees))
                        .contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        then(organizationFacade)
                .should()
                .getEmployees();

        List<EmployeeDto> returned = mapper.readValue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8),
                new TypeReference<List<EmployeeDto>>() {
                });
        assertEquals(expected, returned);
    }

    @Test
    public void getEmployeeByFullNameTest() throws Exception {
        String fullName = "Иванов Иван Иванович";
        Employee employee = new Employee(1L, fullName, new Position(), new ArrayList<>(),
                new ArrayList<>());

        EmployeeDto expected = new EmployeeDto(employee.getId(), employee.getFullName(), new PositionDto());

        when(organizationFacade.getEmployeeByName(fullName)).thenReturn(employee);
        when(employeeConverter.toDto(employee)).thenReturn(expected);

        MvcResult mvcResult = mockMvc.perform(get("/api/v1/organizations/employee")
                        .content(fullName)
                        .contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        then(organizationFacade)
                .should()
                .getEmployeeByName(fullName);

        EmployeeDto returned = mapper.readValue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8),
                new TypeReference<EmployeeDto>() {
                });
        assertEquals(expected, returned);
    }

    @Test
    public void getAllDepartmentsTest() throws Exception {

        Department department1 = new Department(1L, "АХО", new Organization());
        Department department2 = new Department(2L, "ОТО", new Organization());
        List<Department> allDepartments = List.of(department1, department2);

        List<DepartmentDto> expected = allDepartments.stream()
                .map(dep -> new DepartmentDto(dep.getId(), dep.getDepartmentName(),
                        new OrganizationDto(1L, "OAO", 12345, EmployeeDto.builder().build())))
                .collect(Collectors.toList());

        when(organizationFacade.findDepartments()).thenReturn(allDepartments);
        when(departmentConverter.toDto(allDepartments)).thenReturn(expected);

        MvcResult mvcResult = mockMvc.perform(get("/api/v1/organizations/departments")
                        .content(mapper.writeValueAsString(allDepartments))
                        .contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        then(organizationFacade)
                .should()
                .findDepartments();

        List<DepartmentDto> returned = mapper.readValue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8),
                new TypeReference<List<DepartmentDto>>() {
                });
        assertEquals(expected, returned);
    }
}
