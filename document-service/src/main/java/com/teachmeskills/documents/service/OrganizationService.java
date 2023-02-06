package com.teachmeskills.documents.service;

import com.teachmeskills.documents.model.Organization;
import com.teachmeskills.documents.repository.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrganizationService {

    private final OrganizationRepository organizationRepository;

    public Organization getOrganizationByName(String name) {
        return organizationRepository.getOrganizationByName(name);
    }

    public List<Organization> findOrganizations() {
        return organizationRepository.findAll();
    }
}
