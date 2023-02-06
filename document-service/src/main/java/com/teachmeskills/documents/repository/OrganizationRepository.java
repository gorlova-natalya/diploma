package com.teachmeskills.documents.repository;

import com.teachmeskills.documents.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long> {

    Organization getOrganizationByName(String name);
}
