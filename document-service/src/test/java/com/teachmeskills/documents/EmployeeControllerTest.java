package com.teachmeskills.documents;

import com.teachmeskills.documents.controller.EmployeeController;
import com.teachmeskills.documents.converter.EmployeeConverter;
import com.teachmeskills.documents.facade.EmployeeFacade;
import com.teachmeskills.documents.model.Employee;
import com.teachmeskills.documents.model.Position;
import org.example.common.dto.document.EmployeeDto;
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
@WebMvcTest(EmployeeController.class)
@AutoConfigureMockMvc(addFilters = false)
@ContextConfiguration(classes = EmployeeController.class)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper mapper = new ObjectMapper();

    @MockBean
    private EmployeeFacade employeeFacade;

    @MockBean
    private EmployeeConverter employeeConverter;

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

        when(employeeFacade.getEmployees()).thenReturn(allEmployees);
        when(employeeConverter.toDto(allEmployees)).thenReturn(expected);

        MvcResult mvcResult = mockMvc.perform(get("/api/v1/employees")
                        .content(mapper.writeValueAsString(allEmployees))
                        .contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        then(employeeFacade)
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

        when(employeeFacade.getEmployeeByName(fullName)).thenReturn(employee);
        when(employeeConverter.toDto(employee)).thenReturn(expected);

        MvcResult mvcResult = mockMvc.perform(get("/api/v1/employees/Иванов Иван Иванович").characterEncoding("utf-8")
                        .content(fullName)
                        .contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        then(employeeFacade)
                .should()
                .getEmployeeByName(fullName);

        EmployeeDto returned = mapper.readValue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8),
                new TypeReference<EmployeeDto>() {
                });
        assertEquals(expected, returned);
    }
}
