package com.teachmeskills.documents.controller;

import com.teachmeskills.documents.converter.DepartmentConverter;
import com.teachmeskills.documents.facade.DepartmentFacade;
import com.teachmeskills.documents.model.Department;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.common.dto.document.DepartmentDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/departments")
@RequiredArgsConstructor
@Slf4j
public class DepartmentController {

    private final DepartmentFacade departmentFacade;
    private final DepartmentConverter departmentConverter;

    @GetMapping("/all")
    protected List<DepartmentDto> getDepartments() {
        List<Department> departments = departmentFacade.findDepartments();
        return departmentConverter.toDto(departments);
    }
}
