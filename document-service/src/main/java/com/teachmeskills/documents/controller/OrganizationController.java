package com.teachmeskills.documents.controller;

import com.teachmeskills.documents.converter.OrganizationConverter;
import com.teachmeskills.documents.facade.OrganizationFacade;
import org.example.common.dto.document.OrganizationDto;
import com.teachmeskills.documents.model.Organization;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/organizations")
@RequiredArgsConstructor
@Slf4j
public class OrganizationController {

    private final OrganizationFacade organizationFacade;
    private final OrganizationConverter organizationConverter;

    @GetMapping("/all")
    protected List<OrganizationDto> getAllOrganizations() {
        List<Organization> organizations = organizationFacade.findOrganizations();
        return organizationConverter.toDto(organizations);
    }

    @GetMapping("/{name}")
    protected OrganizationDto getOrganization(@PathVariable("name") final String name) {
        Organization organization = organizationFacade.getOrganizationByName(name);
        return organizationConverter.toDto(organization);
    }
}
