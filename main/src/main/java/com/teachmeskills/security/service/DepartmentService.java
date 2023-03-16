package com.teachmeskills.security.service;

import com.teachmeskills.security.client.DepartmentClient;
import lombok.RequiredArgsConstructor;
import org.example.common.dto.document.DepartmentDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentClient departmentClient;

    public List<DepartmentDto> getDepartments() {
        return departmentClient.getDepartments();
    }
}
