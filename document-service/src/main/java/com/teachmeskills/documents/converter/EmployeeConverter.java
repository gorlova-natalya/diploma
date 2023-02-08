package com.teachmeskills.documents.converter;

import com.teachmeskills.documents.dto.EmployeeDto;
import com.teachmeskills.documents.model.Employee;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(uses = PositionConverter.class)

public interface EmployeeConverter {

    EmployeeDto toDto(Employee employee);

    List<EmployeeDto> toDto(List<Employee> employees);

    Employee toEntity(EmployeeDto employeeDto);
}
