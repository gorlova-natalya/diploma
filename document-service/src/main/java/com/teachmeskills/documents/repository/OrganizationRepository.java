package com.teachmeskills.documents.repository;

import com.teachmeskills.documents.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long> {

    Optional<Organization> getOrganizationByName(String name);

    Optional<Organization> getOrganizationById(Long id);
}
