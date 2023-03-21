package com.teachmeskills.documents;

import com.teachmeskills.documents.controller.DepartmentController;
import com.teachmeskills.documents.converter.DepartmentConverter;
import com.teachmeskills.documents.facade.DepartmentFacade;
import com.teachmeskills.documents.model.Department;
import com.teachmeskills.documents.model.Organization;
import org.example.common.dto.document.DepartmentDto;
import org.example.common.dto.document.EmployeeDto;
import org.example.common.dto.document.OrganizationDto;
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
@WebMvcTest(DepartmentController.class)
@AutoConfigureMockMvc(addFilters = false)
@ContextConfiguration(classes = DepartmentController.class)
public class DepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper mapper = new ObjectMapper();

    @MockBean
    private DepartmentFacade departmentFacade;
    @MockBean
    private DepartmentConverter departmentConverter;

    @Test
    public void getAllDepartmentsTest() throws Exception {

        Department department1 = new Department(1L, "АХО", new Organization());
        Department department2 = new Department(2L, "ОТО", new Organization());
        List<Department> allDepartments = List.of(department1, department2);

        List<DepartmentDto> expected = allDepartments.stream()
                .map(dep -> new DepartmentDto(dep.getId(), dep.getDepartmentName(),
                        new OrganizationDto(1L, "OAO", 12345, EmployeeDto.builder().build())))
                .collect(Collectors.toList());

        when(departmentFacade.findDepartments()).thenReturn(allDepartments);
        when(departmentConverter.toDto(allDepartments)).thenReturn(expected);

        MvcResult mvcResult = mockMvc.perform(get("/api/v1/departments")
                        .content(mapper.writeValueAsString(allDepartments))
                        .contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        then(departmentFacade)
                .should()
                .findDepartments();

        List<DepartmentDto> returned = mapper.readValue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8),
                new TypeReference<List<DepartmentDto>>() {
                });
        assertEquals(expected, returned);
    }
}
