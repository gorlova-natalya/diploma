package com.teachmeskills.documents.converter;

import org.example.common.dto.document.OrganizationDto;
import com.teachmeskills.documents.model.Organization;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(uses = EmployeeConverter.class)
public interface OrganizationConverter {

    OrganizationDto toDto(Organization organization);

    List<OrganizationDto> toDto(List<Organization> organizations);
}
