package com.teachmeskills.documents.converter;

import org.example.common.dto.document.DepartmentDto;
import com.teachmeskills.documents.model.Department;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(uses = {OrganizationConverter.class})
public interface DepartmentConverter {

    DepartmentDto toDto(DepartmentDto departmentDto);

    List<DepartmentDto> toDto(List<Department> departments);
}
