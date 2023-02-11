package com.teachmeskills.security.service;

import com.teachmeskills.security.client.OrganizationClient;
import com.teachmeskills.security.dto.EmployeeDto;
import com.teachmeskills.security.dto.OrganizationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrganizationService {

    private final OrganizationClient organizationClient;

    public List<OrganizationDto> getOrganizations() {
        return organizationClient.getAllOrganizations();
    }

    public List<EmployeeDto> getEmployees() {
        return organizationClient.getEmployees();
    }
}
