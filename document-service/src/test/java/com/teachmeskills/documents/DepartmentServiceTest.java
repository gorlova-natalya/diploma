package com.teachmeskills.documents;

import com.teachmeskills.documents.model.Department;
import com.teachmeskills.documents.model.Employee;
import com.teachmeskills.documents.model.Organization;
import com.teachmeskills.documents.repository.DepartmentRepository;
import com.teachmeskills.documents.service.DepartmentService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@Disabled
@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = DepartmentService.class)
public class DepartmentServiceTest {

    @Autowired
    private DepartmentService departmentService;

    @MockBean
    private DepartmentRepository departmentRepository;

    @Test
    public void getAllDepartmentsTest() {

        Department department1 = new Department(1L, "OТО",
                new Organization(1L, "OAO", 1234567, new Employee()));
        Department department2 = new Department(1L, "АХО",
                new Organization(2L, "АХО", 1234569, new Employee()));

        List<Department> allDepartments = List.of(department1, department2);

        given(departmentRepository.findAll()).willReturn(allDepartments);
        List<Department> expected = departmentService.findDepartments();

        assertEquals(allDepartments, expected);

        verify(departmentRepository).findAll();
    }

    @Test
    public void getDepartmentByIdTest() {
        Department department = new Department(1L, "OТО",
                new Organization(1L, "OAO", 1234567, new Employee()));

        final Optional<Department> expected = Optional.of(department);

        given(departmentRepository.findById(department.getId())).willReturn(expected);

        Optional<Department> returned = departmentService.getDepartment(department.getId());

        assertEquals(expected, returned);

        verify(departmentRepository).findById(department.getId());
    }
}
