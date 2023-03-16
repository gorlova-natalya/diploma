package com.teachmeskills.documents.facade;

import com.teachmeskills.documents.model.Department;
import com.teachmeskills.documents.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentFacade {

    private final DepartmentService departmentService;

    public List<Department> findDepartments() {
        return departmentService.findDepartments();
    }
}
