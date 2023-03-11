package com.teachmeskills.documents.repository;

import com.teachmeskills.documents.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> getEmployeeByFullName(String fullName);

    Optional<Employee> getEmployeeById(Long id);
}
