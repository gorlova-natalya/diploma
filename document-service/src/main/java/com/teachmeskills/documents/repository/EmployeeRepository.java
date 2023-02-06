package com.teachmeskills.documents.repository;

import com.teachmeskills.documents.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Employee getEmployeeByFullName(String fullName);
}
