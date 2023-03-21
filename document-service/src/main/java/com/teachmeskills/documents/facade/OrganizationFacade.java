package com.teachmeskills.documents.facade;

import com.teachmeskills.documents.model.Organization;
import com.teachmeskills.documents.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrganizationFacade {

    private final OrganizationService organizationService;

    public List<Organization> findOrganizations() {
        return organizationService.findOrganizations();
    }

    public Organization getOrganizationByName(String name) {
        return organizationService.getOrganizationByName(name).stream().findFirst().orElse(null);
    }
}
