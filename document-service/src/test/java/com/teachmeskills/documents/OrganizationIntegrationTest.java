package com.teachmeskills.documents;

import com.teachmeskills.documents.model.Organization;
import com.teachmeskills.documents.repository.OrganizationRepository;
import org.junit.Test;
import org.junit.jupiter.api.Disabled;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Disabled
public class OrganizationIntegrationTest extends AbstractIntegrationDocumentsTest {

    @Autowired
    private OrganizationRepository organizationRepository;

    @Test
    public void getOrganizationByNameTest() {
        String name = "Минская центральная таможня";

        Optional<Organization> result = organizationRepository.getOrganizationByName(name);

        assertEquals(result.get().getName(), name);
    }

    @Test
    public void getOrganizationByIdTest() {
        Organization organization = new Organization();
        organization.setId(1L);

        Optional<Organization> result = organizationRepository.getOrganizationById(organization.getId());

        assertEquals(result.get().getName(), "Минская центральная таможня");
    }

    @Test
    public void findOrganizationsTest() {
        List<Organization> organizations = organizationRepository.findAll();

        assertThat(organizations)
                .hasSize(1);
    }
}
