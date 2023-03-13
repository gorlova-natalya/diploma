package com.teachmeskills.documents;

import com.teachmeskills.documents.model.Employee;
import com.teachmeskills.documents.model.Organization;
import com.teachmeskills.documents.repository.OrganizationRepository;
import com.teachmeskills.documents.service.OrganizationService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;
import java.util.Optional;

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
    private OrganizationRepository organizationRepository;

    @Test
    public void getAllOrganizationsTest() {

        Organization organization1 = new Organization(1L, "OAO", 1234567, new Employee());
        Organization organization2 = new Organization(2L, "МТЗ", 1234569, new Employee());
        List<Organization> allOrganizations = List.of(organization1, organization2);

        given(organizationRepository.findAll()).willReturn(allOrganizations);
        List<Organization> expected = organizationService.findOrganizations();

        assertEquals(expected, allOrganizations);

        verify(organizationRepository).findAll();
    }

    @Test
    public void getOrganizationByNameTest() {
        Organization organization = new Organization(1L, "OAO", 1234567, new Employee());

        final Optional<Organization> expected = Optional.of(organization);

        given(organizationRepository.getOrganizationByName(organization.getName())).willReturn(expected);

        Optional<Organization> returned = organizationService.getOrganizationByName(organization.getName());

        assertEquals(expected, returned);

        verify(organizationRepository).getOrganizationByName(organization.getName());
    }

    @Test
    public void getOrganizationByIdTest() {
        Organization organization = new Organization(1L, "OAO", 1234567, new Employee());

        final Optional<Organization> expected = Optional.of(organization);

        given(organizationRepository.getOrganizationById(organization.getId())).willReturn(expected);

        Optional<Organization> returned = organizationService.get(organization.getId());

        assertEquals(expected, returned);

        verify(organizationRepository).getOrganizationById(organization.getId());
    }
}
