package com.teachmeskills;

import com.teachmeskills.security.client.DepartmentClient;
import com.teachmeskills.security.service.DepartmentService;
import org.example.common.dto.document.DepartmentDto;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;

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
    private DepartmentClient departmentClient;

    @Test
    public void getAllDepartmentsTest() {
        List<DepartmentDto> departments = new ArrayList<>();

        given(departmentClient.getDepartments()).willReturn(departments);
        List<DepartmentDto> expected = departmentService.getDepartments();

        assertEquals(expected, departments);

        verify(departmentClient).getDepartments();
    }
}
