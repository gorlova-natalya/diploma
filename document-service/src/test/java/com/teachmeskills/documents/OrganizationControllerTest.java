package com.teachmeskills.documents;

import com.teachmeskills.documents.controller.OrganizationController;
import com.teachmeskills.documents.converter.OrganizationConverter;
import com.teachmeskills.documents.facade.OrganizationFacade;
import com.teachmeskills.documents.model.Employee;
import com.teachmeskills.documents.model.Organization;
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

        MvcResult mvcResult = mockMvc.perform(get("/api/v1/organizations/all")
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
        final String name = "OAO";
        Organization organization = new Organization(1L, name, 1234567, new Employee());

        OrganizationDto expected = new OrganizationDto(organization.getId(), organization.getName(),
                organization.getPayerNumber(), new EmployeeDto(1L, "Сотрудник", new PositionDto()));

        when(organizationFacade.getOrganizationByName(name)).thenReturn(organization);
        when(organizationConverter.toDto(organization)).thenReturn(expected);

        MvcResult mvcResult = mockMvc.perform(get("/api/v1/organizations/OAO")
                        .content(name)
                        .contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        then(organizationFacade)
                .should()
                .getOrganizationByName(name);

        OrganizationDto returned = mapper.readValue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8),
                new TypeReference<OrganizationDto>() {
                });
        assertEquals(expected, returned);
    }
}
