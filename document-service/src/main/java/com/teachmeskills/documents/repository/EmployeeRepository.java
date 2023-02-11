package com.teachmeskills.documents.repository;

import com.teachmeskills.documents.model.DocumentType;
import com.teachmeskills.documents.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> getEmployeeByFullName(String fullName);

    List<Employee> getEmployeesBySignedDocuments(DocumentType documentType);

    Optional<Employee> getEmployeeById(Long id);
}
