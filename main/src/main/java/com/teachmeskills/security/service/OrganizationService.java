package com.teachmeskills.security.service;

import com.teachmeskills.security.client.OrganizationClient;
import lombok.RequiredArgsConstructor;
import org.example.common.dto.document.OrganizationDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrganizationService {

    private final OrganizationClient organizationClient;

    public List<OrganizationDto> getOrganizations() {
        return organizationClient.getAllOrganizations();
    }
}
