package com.teachmeskills.documents.service;

import com.teachmeskills.documents.model.Department;
import com.teachmeskills.documents.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public List<Department> findDepartments() {
        return departmentRepository.findAll();
    }

    public Optional<Department> getDepartment(Long id) {
        return departmentRepository.findById(id);
    }
}
