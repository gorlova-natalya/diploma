package com.teachmeskills.documents;

import com.teachmeskills.documents.model.Department;
import com.teachmeskills.documents.model.Employee;
import com.teachmeskills.documents.model.Organization;
import com.teachmeskills.documents.repository.DepartmentRepository;
import org.junit.Test;
import org.junit.jupiter.api.Disabled;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Disabled
public class DepartmentIntegrationTest extends AbstractIntegrationDocumentsTest {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Test
    public void getDepartmentByIdTest() {
        Department department = new Department(1L, "OТО",
                new Organization(1L, "OAO", 1234567, new Employee()));
        departmentRepository.save(department);

        Optional<Department> result = departmentRepository.findById(department.getId());

        assertEquals(result.get().getId(), department.getId());
    }

    @Test
    public void findDepartmentsTest() {
        List<Department> departments = departmentRepository.findAll();

        assertThat(departments)
                .hasSize(6);
    }
}
