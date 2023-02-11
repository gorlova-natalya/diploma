package com.teachmeskills.documents.service;

import com.teachmeskills.documents.model.Organization;
import com.teachmeskills.documents.repository.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrganizationService {

    private final OrganizationRepository organizationRepository;

    public Optional<Organization> getOrganizationByName(String name) {
        return organizationRepository.getOrganizationByName(name);
    }

    public List<Organization> findOrganizations() {
        return organizationRepository.findAll();
    }

    public Optional<Organization> get(Long id) {
        return organizationRepository.getOrganizationById(id);
    }
}
